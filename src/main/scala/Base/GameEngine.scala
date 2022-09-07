package Base

import Base.Player.Player
import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine(player1: Player, player2: Player, gameType: String) {
  val gameController: Controller = null
  val gameDrawer: Drawer = null
  var gameBoard: Array[Array[Piece]]
  var turn: Array[Int] = Array(0)

  def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    player1.color = 1
    gameDrawer.drawPiece()
    player1.run(gameBoard, turn, gameController, gameDrawer)
    player2.run(gameBoard, turn, gameController, gameDrawer)
    gameDrawer.setEvents(Movement)
  }

  def update(): Unit = {
    turn(0) = 1 - turn(0)
    if (player1.color != turn(0) && !player1.isInstanceOf[Player])
      player1.DisableMovement()
    else if (!player2.isInstanceOf[Player])
      player2.DisableMovement()
  }

  def Movement(source: Node): Unit = {}
}
