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
    gameDrawer.draw()
  }

  def movementCheck(): Unit = ???
}
