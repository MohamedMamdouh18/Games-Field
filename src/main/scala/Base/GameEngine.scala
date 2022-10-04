package Base

import javafx.scene.Node
import javafx.scene.layout.StackPane

abstract class GameEngine(players: Array[Player], gameType: String) {
  var gameBoard: Array[Array[Piece]]
  var score: Array[Int]
  var turn: Int = 0
  var gameEnded: Boolean = false

  /***
   * Initialize the game with its GUI and logic.
   * @param gamePane the stack pane that will hold the board in the GUI.
   */
  def startGame(gamePane: StackPane): Unit

  /***
   * Sets the movement on the board if the game doesn't have pieces.
   * @param source Node that the event will run on it.
   */
  def Movement(source: Node): Unit = {}

  /***
   * Updates the game loop.
   */
  def update(): Unit

  /***
   * Executes player move.
   * @param source Node that will change in the GUI.
   */
  def setPlayerMove(source: Node = null): Unit = {}

  /***
   * Runs the AI agent to play in its turn if there is one in the game.
   */
  def play(): Unit
}
