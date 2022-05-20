package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane

abstract class Controller {
  var gameBoard: Array[Array[String]]
  var PlayerRole: Int = 1
  var board: GridPane = new GridPane()
  var x, y: Double = 0
  var oldRow, oldCol: Int = 0

  def Movement(source: Node): Unit = ???

  def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = ???

  def setBoard(board: GridPane): Unit = {
    this.board = board
  }

  def setGameBoard(board: Array[Array[String]]): Unit = {
    this.gameBoard = board
  }
}
