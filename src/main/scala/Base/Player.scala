package Base

import javafx.scene.Node
import javafx.scene.layout.GridPane

abstract class Player {
  var observer: GameEngine = _
  var gameBoard: Array[Array[Piece]] = _
  var color: Int = 0
  var depth: Int = 0

  /**
   * Initializes the player data at the begin of the game.
   *
   * @param buts promotion buttons if exists.
   */
  def run(buts: GridPane = null): Unit

  /**
   * Sets the depth of the AI if exists.
   *
   * @param dep the AI's depth.
   */
  def setDepth(dep: Int): Unit = {
    depth = dep
  }

  /**
   * Notifies the engine to execute the player's play and continues the game.
   */
  def Notify(): Unit = {
    observer.update()
  }

  /**
   * Sets the movement on the board if the game has pieces.
   *
   * @param source Node that the event will run on it.
   */
  def Movement(source: Node = null): Unit = {}

  /**
   * Disables the events which the player can do.
   */
  def DisableMovement(): Unit = {}

  /**
   * Sets the observer of the game which is the engine to update the game when it is notified.
   *
   * @param gameEngine the engine that executes the player's moves and update the game loop.
   */
  def setObserver(gameEngine: GameEngine): Unit = {
    observer = gameEngine
  }
}
