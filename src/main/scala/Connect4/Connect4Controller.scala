package Connect4

import Base.{Controller, MoveValidation, Piece, State}

class Connect4Controller extends Controller {
  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    var i = 0
    while (i < 6) {
      if (gameBoard(i)(state.oldCol) == null) {
        gameBoard(i)(state.oldCol) = if (state.turn == 1)
          new Piece(Connect4PieceEn.Red, i, state.oldCol, 1)
        else
          new Piece(Connect4PieceEn.Yellow, i, state.oldCol, 0)
        return new MoveValidation(new State(i, state.oldCol, 0, 0, 0), true)
      } else
        i += 1
    }
    new MoveValidation(null, false)
  }

  override def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean = ???
}
