package Chess

import Base.{Controller, Drawer, GameEngine, Piece, State}
import Base.Player.Player
import javafx.scene.Node
import javafx.scene.layout.GridPane

class AIChess extends Player {
  private var gameController: Controller = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _
  override var observer: GameEngine = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {}

  private def miniMax(board: Array[Array[Piece]], t: Int, depth: Int): State = {
    new State(0, 0, 0, 0, 0)
  }

  private def estimator(board: Array[Array[Piece]]): Unit = {

  }
}
