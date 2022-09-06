package Base

import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine(player1: Player, player2: Player, gameType: String) {
  val gameController: Controller = null
  val gameDrawer: Drawer = null
  var gameBoard: Array[Array[Piece]]
  var turn: Array[Int] = Array(0)

  def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    gameDrawer.drawPiece()
    gameDrawer.setEvents(Movement)
  }

  def update(): Unit = {

  }

  def Movement(source: Node): Unit = {}
}
