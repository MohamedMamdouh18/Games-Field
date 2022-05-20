package GameEngines.Engines

import GameEngines.Drawers.Drawer
import GameEngines.GamesControllers.Controller
import javafx.scene.layout.StackPane

abstract class GameEngine {
  var gameBoard: Array[Array[String]]
  val gameController: Controller = null
  val gameDrawer: Drawer = null

  def startGame(gamePane: StackPane): Unit = {
//    println("Starting Game")

    gameDrawer.setGamePane(gamePane)

    gameController.setGameBoard(gameBoard)
    gameDrawer.setGameBoard(gameBoard)

    gameDrawer.setDrag(gameController.Movement)

    val board = gameDrawer.draw()
    gameController.setBoard(board)

//    println(board.localToScene(board.getBoundsInLocal))
  }
}
