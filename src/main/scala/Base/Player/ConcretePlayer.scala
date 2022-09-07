package Base.Player

import Base.{Controller, Drawer, GameEngine, Piece}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class ConcretePlayer extends Player {
  override var observer: GameEngine = _
  private var gameController: Controller = _
  private var gameBoard: Array[Array[Piece]] = _
  private var gameDrawer: Drawer = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard
  }

  def Movement(source: Node): Unit = {}
}
