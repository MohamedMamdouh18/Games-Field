package GameEngines

import GameEngines.Drawers.Drawer
import GameEngines.GamesControllers.Controller
import javafx.scene.layout.StackPane

abstract class GameEngine {
  val gameController: Controller = null
  val gameDrawer: Drawer = null

  def startGame(gamePane: StackPane): Unit = {
    println("Starting Game")

    gameDrawer.setGamePane(gamePane)
    val board= gameDrawer.draw()
    gameController.movement(board)
    movementCheck()
  }


  def movementCheck(): Unit = ???
}
