package Controller

import Drawers.Drawer
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class GameEngine(drawer: Drawer, controller: Controller) {
  def startGame(gamePane: StackPane): Unit = {
    println("Starting Chess")

    drawer.setGamePane(gamePane)
    drawer.draw()
  }

}
