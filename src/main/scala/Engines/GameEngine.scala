package Engines

import Controllers.Controller
import Drawers.Drawer
import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine {
  var gameBoard: Array[Array[String]]
  val gameController: Controller = null
  val gameDrawer: Drawer = null

  def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)

    gameController.setGameBoard(gameBoard)
    gameDrawer.setGameBoard(gameBoard)

    gameDrawer.setDrag(Movement)

    val board = gameDrawer.draw()
    gameController.setBoard(board)

//    println(board.localToScene(board.getBoundsInLocal))
  }

  def Movement(source: Node): Unit
}
