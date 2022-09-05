package Base

abstract class Controller {
  def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation

  def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean
}
