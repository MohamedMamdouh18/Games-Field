package Controllers

import javafx.scene.layout.GridPane

abstract class Controller {
  var gameBoard: Array[Array[String]]
  var PlayerRole: Int = 1
  var board: GridPane = new GridPane()

  def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean

  def setBoard(board: GridPane): Unit = {
    this.board = board
  }

  def setGameBoard(board: Array[Array[String]]): Unit = {
    this.gameBoard = board
  }
}
