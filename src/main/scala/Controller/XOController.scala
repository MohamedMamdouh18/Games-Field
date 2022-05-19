package Controller

import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class XOController extends GameEngine {

  override var gamePane: StackPane = new StackPane()

  def startGame(gamePane: StackPane): Unit = {
    println("Starting Chess")

    setGamePane(gamePane)
    val chessBoard = drawBoard(3, 3, Color.BLACK, Color.BLACK, showGridLines = false)
  }
}
