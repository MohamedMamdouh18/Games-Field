package Engines

import Base.Piece
import Controllers.Controller
import Drawers.Drawer
import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine {
  val gameController: Controller = null
  val gameDrawer: Drawer = null
  var gameBoard: Array[Array[Piece]]
  var turn: Int = 0

  def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    gameDrawer.setDrag(Movement)
    gameDrawer.draw()
  }

  def Movement(source: Node): Unit
}
