package XO

import Base.Player.Player
import Base._
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class XOAI extends Player {
  override var observer: GameEngine = _
  private var gameController: Controller = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {
    if (observer.gameEnded)
      return

    val move: State = miniMax(gameBoard, color, Int.MinValue, Int.MaxValue).getValue
    println(move.oldRow)
    var src: Node = null

    gameDrawer.gameBoard.getChildren.forEach(node => {
      if (GridPane.getColumnIndex(node) == move.oldCol && GridPane.getRowIndex(node) == move.oldRow) {
        src = node
        gameBoard(move.oldRow)(move.oldCol) = new Piece(if (observer.turn == 0) XOEn.X else XOEn.O,
          move.oldRow, move.oldCol, observer.turn)
        move.turn = observer.turn
        gameDrawer.movementDraw(src, move)
        Notify()
        return
      }
    })
  }

  private def miniMax(board: Array[Array[Piece]], turn: Int, a: Int, b: Int): Pair[Int, State] = {
    println(a, b)
    if (gameController.checkEndGame(board))
      return new Pair[Int, State](if (turn == 0) -1 else 1, null)
    else if (gameController.checkTie(board))
      return new Pair[Int, State](0, null)

    var score: Int = if (turn == 0) Int.MinValue else Int.MaxValue
    var alpha: Int = a
    var beta: Int = b
    var bestMove: State = null

    for (i <- 0 until 3) {
      for (j <- 0 until 3) {
        if (board(i)(j) == null) {
          board(i)(j) = new Piece(if (turn == 0) XOEn.X else XOEn.O, i, j, turn)

          val newScore: Int = miniMax(board, 1 - turn, alpha, beta).getKey
          board(i)(j) = null

          if (turn == 0) {
            if (newScore > score) {
              score = newScore
              bestMove = new State(i, j, -1, -1, turn)
            }
            alpha = Math.max(alpha, score)
            if (alpha >= beta)
              return new Pair[Int, State](score, bestMove)
          } else {
            if (newScore < score) {
              score = newScore
              bestMove = new State(i, j, -1, -1, turn)
            }
            beta = Math.min(beta, score)
            if (alpha >= beta)
              return new Pair[Int, State](score, bestMove)
          }
        }
      }
    }

    new Pair[Int, State](score, bestMove)
  }
}
