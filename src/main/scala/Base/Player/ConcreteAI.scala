package Base.Player

import Base._
import javafx.scene.Node
import javafx.scene.layout.GridPane

class ConcreteAI extends Player {
  var gameController: Controller = _
  var gameDrawer: Drawer = _
  var gameBoard: Array[Array[Piece]] = _
  override var observer: GameEngine = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard
  }

  override def Movement(source: Node): Unit = {}

  def miniMax(board: Array[Array[Piece]], t: Int, depth: Int): State = {
    new State(0, 0, 0, 0, 0)
  }

  def estimator(board: Array[Array[Piece]]): Unit = {

  }
}
