package Base.Player

import Base._
import javafx.scene.Node
import javafx.scene.layout.GridPane

class ConcreteAI extends Player {
  private var gameController: Controller = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _
  override var observer: GameEngine = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {}

  private def miniMax(board: Array[Array[Piece]], t: Int, depth: Int): State = {
    new State(0, 0, 0, 0, 0)
  }

  private def estimator(board: Array[Array[Piece]]): Unit = {

  }
}
