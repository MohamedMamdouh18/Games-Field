package Controller

import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class ChessController extends GameEngine {

  override var gamePane: StackPane = new StackPane()

  def startGame(gamePane: StackPane): Unit = {
    println("Starting Chess")

    setGamePane(gamePane)
    val chessBoard = drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
  }
}
