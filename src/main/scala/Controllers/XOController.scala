package Controllers

import Base.{MoveValidation, Piece, State}

class XOController extends Controller {

  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    if (gameBoard(state.oldCol)(state.oldRow) == null) {
      gameBoard(state.oldCol)(state.oldRow) = if (state.turn == 1)
        new Piece("X", state.oldRow, state.oldCol, 1)
      else
        new Piece("O", state.oldRow, state.oldCol, 0)
      new MoveValidation(null, true)
    } else
      new MoveValidation(null, false)
  }
}
