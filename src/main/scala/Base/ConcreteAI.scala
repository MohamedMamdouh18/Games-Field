package Base
import javafx.scene.Node
import javafx.scene.layout.GridPane

class ConcreteAI extends Player {
  override var gameController: Controller = _
  override var gameDrawer: Drawer = _
  override var gameBoard: Array[Array[Piece]] = _
  override var observer: GameEngine = _
  override var turn: Array[Int] = _

  override def run(board: Array[Array[Piece]], turn: Array[Int], controller: Controller, drawer: Drawer, buts: GridPane): Unit = {

  }

  override def Movement(source: Node): Unit = {}
}
