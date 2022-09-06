package Base

import javafx.scene.Node
import javafx.scene.layout.HBox

abstract class Player {
  val gameController: Controller
  var gameDrawer: Drawer
  var gameBoard: Array[Array[Piece]]
  var observer: GameEngine
  var color: Int = 0
  var turn: Array[Int]

  def run(board: Array[Array[Piece]], turn: Array[Int], drawer: Drawer = null, buts: HBox = null): Unit

  def Notify(): Unit

  def Movement(source: Node): Unit

  def setObserver(gameEngine: GameEngine): Unit
}
