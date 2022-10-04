package Connect4

import Base._
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class Connect4AI extends Player {
  private val turns: Array[String] = Array(Connect4En.Yellow, Connect4En.Red)

  override def run(buts: GridPane = null): Unit = {
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {
    val move = miniMax(gameBoard, color, -1000, 1000, depth).getKey

    gameBoard(move.oldRow)(move.oldCol) = new Piece(turns(color), move.oldRow, move.oldCol, color)

    var src: Node = null
    Connect4Drawer.gameBoard.getChildren.forEach(node => {
      if (GridPane.getColumnIndex(node) == move.oldCol && GridPane.getRowIndex(node) == move.oldRow) {
        src = node
        Connect4Drawer.movementDraw(src, move)
        Notify()
        return
      }
    })
  }

  /**
   * Calculates the best move for the AI for the current game board and the function continue recursion until the end of the game or the depth reaches 0.
   *
   * @param board the board of the game which has been played so far.
   * @param turn  turn of the current player to find best move for.
   * @param a     the alpha value that we want to maximize.
   * @param b     the beta value that we want to minimize.
   * @param depth the number of consecutive games to calculate the best move for.
   * @return the best move for the given board.
   */
  private def miniMax(board: Array[Array[Piece]], turn: Int, a: Int, b: Int, depth: Int): Pair[State, Int] = {
    if (Connect4Controller.checkEndGame(board, turn))
      return new Pair[State, Int](null, if (turn == 0) -100 else 100)
    else if (Connect4Controller.checkEndGame(board, 1 - turn))
      return new Pair[State, Int](null, if (1 - turn == 0) -100 else 100)
    else if (Connect4Controller.checkTie(board))
      return new Pair[State, Int](null, 0)
    if (depth == 0)
      return new Pair[State, Int](null, if (turn == 0) estimate(gameBoard, turn) * -1 else estimate(gameBoard, turn))

    var score = if (turn == 0) 1000 else -1000
    var bestMove: State = null
    var alpha: Int = a
    var beta: Int = b

    for (col <- 0 until Connect4En.ColLen) {
      val freeRow = getRow(board, col)
      if (freeRow < Connect4En.RowLen) {
        board(freeRow)(col) = new Piece(turns(turn), freeRow, col, turn)

        val curScore: Pair[State, Int] = miniMax(board, 1 - turn, alpha, beta, depth - 1)
        board(freeRow)(col) = null

        if (turn == 0) {
          if (curScore.getValue < score) {
            score = curScore.getValue
            bestMove = new State(freeRow, col, 0, 0, turn)
          }
          beta = Math.min(beta, score)
        } else {
          if (curScore.getValue > score) {
            score = curScore.getValue
            bestMove = new State(freeRow, col, 0, 0, turn)
          }
          alpha = Math.max(alpha, score)
        }
        if (alpha >= beta)
          return new Pair[State, Int](bestMove, score)
      }
    }
    new Pair[State, Int](bestMove, score)
  }

  /**
   * Returns the first empty row in specific column which is valid to play in.
   *
   * @param board the game board to get its empty row in the given column.
   * @param Col   the specific column to get its empty row.
   * @return the first empty row in a column.
   */
  private def getRow(board: Array[Array[Piece]], Col: Int): Int = {
    for (i <- Connect4En.RowLen - 1 to 0 by -1) {
      if (board(i)(Col) != null)
        return i + 1
    }
    0
  }

  /**
   * Returns the score of the given game board for a specific player.
   *
   * @param gameBoard the board of the game which has been played so far.
   * @param turn      the turn of player to estimate the score for.
   * @return the estimated score of the given game board.
   */
  private def estimate(gameBoard: Array[Array[Piece]], turn: Int): Int = {
    var score: Int = 0

    var centerCount: Int = 0
    for (row <- 0 until Connect4En.RowLen)
      if (gameBoard(row)(3) != null && gameBoard(row)(3).color == turn) centerCount += 1
    score += centerCount * 3

    for (col <- 0 until Connect4En.ColLen) {
      for (row <- 0 until Connect4En.RowLen - 3) {
        var window: Array[Piece] = Array[Piece]()
        for (i <- 0 until Connect4En.WindowLen)
          window = window :+ gameBoard(row + i)(col)
        score += evaluateWindow(window, turn)
      }
    }

    for (row <- 0 until Connect4En.RowLen) {
      for (col <- 0 until Connect4En.ColLen - 3) {
        var window: Array[Piece] = Array[Piece]()
        for (i <- 0 until Connect4En.WindowLen)
          window = window :+ gameBoard(row)(col + i)
        score += evaluateWindow(window, turn)
      }
    }

    for (row <- 0 until Connect4En.RowLen - 3) {
      for (col <- 0 until Connect4En.ColLen - 3) {
        var window: Array[Piece] = Array[Piece]()
        for (i <- 0 until Connect4En.WindowLen)
          window = window :+ gameBoard(row + i)(col + i)
        score += evaluateWindow(window, turn)
      }
    }

    for (row <- 0 until Connect4En.RowLen - 3) {
      for (col <- 0 until Connect4En.ColLen - 3) {
        var window: Array[Piece] = Array[Piece]()
        for (i <- 0 until Connect4En.WindowLen)
          window = window :+ gameBoard(row + 3 - i)(col + i)
        score += evaluateWindow(window, turn)
      }
    }
    score
  }

  /**
   * Returns the score of given window -which is group of four consecutive cells- for a player.
   *
   * @param window a group of four consecutive cells to estimate its score.
   * @param turn   turn of the player to calculate its score.
   * @return Returns the score of given window of cells.
   */
  private def evaluateWindow(window: Array[Piece], turn: Int): Int = {
    var empty: Int = 0
    var enemy: Int = 0
    var ally: Int = 0
    var score: Int = 0
    for (i <- window.indices) {
      if (window(i) == null) empty += 1
      else if (window(i).color == turn) ally += 1
      else enemy += 1
    }
    if (ally == 4) score += 100
    else if (ally == 3 && empty == 1) score += 5
    else if (ally == 2 && empty == 2) score += 2

    if (enemy == 3 && empty == 1) score -= 4

    score
  }
}
