package Controllers

import Base.{MoveValidation, Piece, State}

abstract class Controller {
  def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation
}
