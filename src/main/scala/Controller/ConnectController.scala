package Controller

import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class ConnectController extends GameEngine {

  override var gamePane: StackPane = new StackPane()

  def startGame(gamePane: StackPane): Unit = {
    println("Starting Chess")

    setGamePane(gamePane)
    val connectBoard = drawBoard(6, 7, Color.BLUE, Color.BLUE, showGridLines = false)
  }


}
