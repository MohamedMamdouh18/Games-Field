package Base

import Base.Player.Player
import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine(player1: Player, player2: Player, gameType: String) {
  val gameController: Controller = null
  val gameDrawer: Drawer = null
  var gameBoard: Array[Array[Piece]]
  var score: Array[Int]
  var turn: Int = 0
  var gameEnded: Boolean = false

  def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    player1.color = 1
    gameDrawer.drawPiece()
    player1.run()
    player2.run()
    gameDrawer.setEvents(Movement)
  }

  def update(): Unit = {
    turn = 1 - turn
    if (player1.color != turn && !player1.isInstanceOf[Player])
      player1.DisableMovement()
    else if (!player2.isInstanceOf[Player])
      player2.DisableMovement()
  }

  def Movement(source: Node): Unit = {}
}
