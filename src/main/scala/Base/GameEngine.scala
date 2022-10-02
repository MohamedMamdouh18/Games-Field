package Base

import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine(players: Array[Player], gameType: String) {
  var gameBoard: Array[Array[Piece]]
  var score: Array[Int]
  var turn: Int = 0
  var gameEnded: Boolean = false

  def startGame(gamePane: StackPane): Unit

  def Movement(source: Node): Unit = {}

  def update(): Unit

  def setPlayerMove(source: Node = null): Unit = {}

  def play(): Unit
}
