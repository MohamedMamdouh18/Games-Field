package Chess

import Base.Player.Player
import Base._
import Chess.Pieces.ChessPiece
import javafx.scene.Node
import javafx.scene.layout.GridPane

class AIChess extends Player {
  override var observer: GameEngine = _
  private var gameController: Controller = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {}

  private def miniMax(board: Array[Array[Piece]], t: Int, depth: Int): State = {
    new State(0, 0, 0, 0, 0)
  }

  private def estimator(board: Array[Array[Piece]]): Int = {
    var score: Array[Int] = Array(0, 0)
    board.foreach(_.foreach(piece => {
      if (piece != null)
        score(piece.color) += piece.asInstanceOf[ChessPiece].rank
    }))
    score(0) - score(1)
  }
}
