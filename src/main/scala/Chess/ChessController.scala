package Chess

import Base.{Controller, MoveValidation, Piece, State}

class ChessController extends Controller {
  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    new MoveValidation(new State(0, 0, 0, 0, 0), false)
  }
}
