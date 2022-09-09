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
  val turns: Array[String] = Array(Connect4En.Yellow, Connect4En.Red)

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {
    val move = miniMax(gameBoard, color, if (color == 0) 5 else -5, 6).getKey


    gameBoard(move.oldRow)(move.oldCol) = new Piece(turns(color), move.oldRow, move.oldCol, color)

//    for (i <- gameBoard.indices) {
//      for (j <- gameBoard(i).indices) {
//        if (gameBoard(i)(j) == null) print(" | ")
//        else print(" " + gameBoard(i)(j).name(0) + " ")
//      }
//      println()
//    }
//    println("..................")

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

  private def miniMax(board: Array[Array[Piece]], turn: Int, bestScore: Int, depth: Int): Pair[State, Int] = {
    if (gameController.checkEndGame(board, 1 - turn)) {
      return new Pair[State, Int](null, if (1 - turn == 0) -4 else 4)
    }
    if (checkTie(board)) {
      return new Pair[State, Int](null, 0)
    }

    var score = if (turn == 0) 5 else -5
    var bestMove: State = null

    for (col <- 0 until 7) {
      if (board(5)(col) == null) {
        val freeRow = getRow(board, col)
        board(freeRow)(col) = new Piece(turns(turn), freeRow, col, turn)

        var curScore: Pair[State, Int] = null
        if (depth == 0)
          curScore = new Pair[State, Int](null, if (turn == 0) estimate(board, turn) * -1 else estimate(board, turn))
        else
          curScore = miniMax(board, 1 - turn, score, depth - 1)
        board(freeRow)(col) = null

        if (turn == 0) {
          if (curScore.getValue < score) {
            score = curScore.getValue
            bestMove = new State(freeRow, col, 0, 0, turn)
          }
          if (curScore.getValue < bestScore) {
            return new Pair[State, Int](bestMove, score)
          }
        } else {
          if (curScore.getValue > score) {
            score = curScore.getValue
            bestMove = new State(freeRow, col, 0, 0, turn)
          }
          if (curScore.getValue > bestScore) {
            return new Pair[State, Int](bestMove, score)
          }
        }
      }
    }
    new Pair[State, Int](bestMove, score)
  }

  private def getRow(board: Array[Array[Piece]], Col: Int): Int = {
    for (i <- 5 to 0 by -1) {
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
    val turns: Array[String] = Array(Connect4En.Yellow, Connect4En.Red)
    val dx: Array[Int] = Array(1, 1, 0, 0, -1, -1, 1, -1)
    val dy: Array[Int] = Array(1, -1, 1, -1, -1, 1, 0, 0)
    var score: Int = 0

    gameBoard.foreach(_.foreach(piece => {
      if (piece != null && piece.name == turns(turn)) {
        for (i <- 0 until 8) {
          score = math.max(score, checkDirection(gameBoard, piece.curRow, piece.curCol, dx(i), dy(i)))
          if (score == 4) {
            return 4
          }
        }
      }
    }))
    score
  }

  private def checkDirection(gameBoard: Array[Array[Piece]], row: Int, col: Int, i: Int, j: Int): Int = {
    val turn = gameBoard(row)(col).name
    var score: Int = 1
    for (move <- 1 to 3) {
      val newRow = row + move * i
      val newCol = col + move * j
      if (newRow < 0 || newRow > 5 || newCol < 0 || newCol > 6
        || gameBoard(newRow)(newCol) == null || gameBoard(newRow)(newCol).name != turn) {
        return score
      }
      score += 1
    }
    4
  }
}
