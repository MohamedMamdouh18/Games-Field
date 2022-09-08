package Base

import Base.Player.{ConcreteAI, ConcretePlayer, Player}
import Chess.{AIChess, ChessEn}
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
    players(1 - turn).color = 1
    gameDrawer.drawPiece()

    players.foreach(player => player.run())
    gameDrawer.setEvents(Movement)

    play()
  }

  def Movement(source: Node): Unit = {}

  def update(state: State = null): Unit = {
    turn = 1 - turn

    if (gameController.checkEndGame(gameBoard, state = state)) {
      gameEnded = true
      players.foreach(player => player.DisableMovement())
    }

    play()
  }

  def play(): Unit
}
