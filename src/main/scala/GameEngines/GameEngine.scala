package GameEngines

import Drawers.Drawer
import GameEngines.GamesControllers.Controller
import javafx.scene.layout.StackPane

class GameEngine(drawer: Drawer, controller: Controller) {
  def startGame(gamePane: StackPane): Unit = {
    println("Starting Game")

    drawer.setGamePane(gamePane)
    drawer.draw()
  }
}
