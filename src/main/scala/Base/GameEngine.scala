package Base

import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine {
  val gameController: Controller = null
  val gameDrawer: Drawer = null
  var gameBoard: Array[Array[Piece]]
  var turn: Int = 0

  def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    gameDrawer.setEvent(Movement)
    gameDrawer.drawPiece()
  }

  def Movement(source: Node): Unit
}
