package Base.Player

import Base.{Controller, Drawer, GameEngine, Piece}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class ConcretePlayer extends Player {
  var gameController: Controller = _
  var gameDrawer: Drawer = _
  var gameBoard: Array[Array[Piece]] = _
  var observer: GameEngine = _

  def run(board: Array[Array[Piece]], controller: Controller, drawer: Drawer, buts: GridPane = null): Unit = {
    gameDrawer = drawer
    gameController = controller
    gameBoard = board
  }

  def Movement(source: Node): Unit = {}
}
