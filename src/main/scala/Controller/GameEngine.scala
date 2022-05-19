package Controller

import Drawers.Drawer
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class GameEngine(drawer: Drawer, controller: Controller) {
  def startGame(gamePane: StackPane): Unit = {
    println("Starting Chess")

    drawer.setGamePane(gamePane)
    val board = drawer.drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
  }

}
