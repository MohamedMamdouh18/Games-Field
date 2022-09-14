package Base

import Base.Player.Player
import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine(players: Array[Player], gameType: String) {
  val gameController: Controller = null
  val gameDrawer: Drawer = null
  var gameBoard: Array[Array[Piece]]
  var score: Array[Int]
  var turn: Int = 0
  var gameEnded: Boolean = false

  def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    players(1).color = 1
    gameDrawer.drawPiece()

    players.foreach(player => player.run())
    gameDrawer.setEvents(Movement)

    play()
  }

  def Movement(source: Node): Unit = {}

  def update(): Unit = {
    turn = 1 - turn

    if (gameController.checkEndGame(gameBoard, 1 - turn)) {
      gameEnded = true
      players.foreach(player => player.DisableMovement())
      gameDrawer.drawEnd(1 - turn)
    }

    if (gameController.checkTie(gameBoard)) {
      gameEnded = true
      gameDrawer.drawEnd(-1)
    }

    play()
  }

  def play(): Unit
}
