package Connect4

import Base.Player.Player
import Base._
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class Connect4AI extends Player {
  override var observer: GameEngine = _
  private var gameController: Controller = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _
  private val turns: Array[String] = Array(Connect4En.Yellow, Connect4En.Red)

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {
    val move = miniMax(gameBoard, color, -1000, 1000, 6).getKey

    gameBoard(move.oldRow)(move.oldCol) = new Piece(turns(color), move.oldRow, move.oldCol, color)

    var src: Node = null
    gameDrawer.gameBoard.getChildren.forEach(node => {
      if (GridPane.getColumnIndex(node) == move.oldCol && GridPane.getRowIndex(node) == move.oldRow) {
        src = node
        gameDrawer.movementDraw(src, move)
        Notify()
        return
      }
    })
  }

  private def miniMax(board: Array[Array[Piece]], turn: Int, a: Int, b: Int, depth: Int): Pair[State, Int] = {
    if (gameController.checkEndGame(board, turn))
      return new Pair[State, Int](null, if (turn == 0) -100 else 100)
    else if (gameController.checkEndGame(board, 1 - turn))
      return new Pair[State, Int](null, if (1 - turn == 0) -100 else 100)
    else if (checkTie(board))
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
        if (alpha >= beta) {
          return new Pair[State, Int](bestMove, score)
        }
      }
    }
    new Pair[State, Int](bestMove, score)
  }

  private def getRow(board: Array[Array[Piece]], Col: Int): Int = {
    for (i <- Connect4En.RowLen - 1 to 0 by -1) {
      if (board(i)(Col) != null)
        return i + 1
    }
    0
  }

  private def checkTie(board: Array[Array[Piece]]): Boolean = {
    board.foreach(_.foreach(piece => {
      if (piece == null) {
        return false
      }
    }))
    true
  }

  private def estimate(gameBoard: Array[Array[Piece]], turn: Int): Int = {
    var score: Int = 0

    //Score center column
    var centerCount: Int = 0
    for (row <- 0 until Connect4En.RowLen)
      if (gameBoard(row)(3) != null && gameBoard(row)(3).color == turn) centerCount += 1
    score += centerCount * 3

    //vertical score
    for (col <- 0 until Connect4En.ColLen) {
      for (row <- 0 until Connect4En.RowLen - 3) {
        var window: Array[Piece] = Array[Piece]()
        for (i <- 0 until Connect4En.WindowLen)
          window = window :+ gameBoard(row + i)(col)
        score += evaluateWindow(window, turn)
      }
    }

    //horizontal score
    for (row <- 0 until Connect4En.RowLen) {
      for (col <- 0 until Connect4En.ColLen - 3) {
        var window: Array[Piece] = Array[Piece]()
        for (i <- 0 until Connect4En.WindowLen)
          window = window :+ gameBoard(row)(col + i)
        score += evaluateWindow(window, turn)
      }
    }

    //diagonal score
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
