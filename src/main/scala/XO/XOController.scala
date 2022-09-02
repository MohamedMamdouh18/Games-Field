package XO

import Base.{Controller, MoveValidation, Piece, State}

class XOController extends Controller {

  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    if (gameBoard(state.oldCol)(state.oldRow) == null) {
      gameBoard(state.oldCol)(state.oldRow) = if (state.turn == 1)
        new Piece(XOPieceEn.X, state.oldRow, state.oldCol, 1)
      else
        new Piece(XOPieceEn.O, state.oldRow, state.oldCol, 0)
      new MoveValidation(null, true)
    } else
      new MoveValidation(null, false)
  }
}
