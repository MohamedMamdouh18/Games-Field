package Controller

import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class CheckersController extends GameEngine {

  override var gamePane: StackPane = new StackPane()

  def startGame(gamePane: StackPane): Unit = {
    println("Starting Chess")

    setGamePane(gamePane)
    val chessBoard = drawBoard(8, 8, Color.WHITE, Color.BLACK, showGridLines = false)
  }
}
