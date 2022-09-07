package Base.Player

import Base.{Controller, Drawer, GameEngine, Piece}
import Chess.{ChessController, ChessDrawer}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class ConcretePlayer extends Player {
  override var observer: GameEngine = _
  var gameController: Controller = _
  var gameBoard: Array[Array[Piece]] = _
  var gameDrawer: Drawer = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard
  }

  def Movement(source: Node): Unit = {}
}
