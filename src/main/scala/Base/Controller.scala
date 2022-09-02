package Base

abstract class Controller {
  def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation
}
