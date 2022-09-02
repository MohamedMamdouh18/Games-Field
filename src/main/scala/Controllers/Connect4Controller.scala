package Controllers

import Base.{MoveValidation, Piece, State}

class Connect4Controller extends Controller {
  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    var i = 0
    while (i < 6) {
      if (gameBoard(i)(state.oldCol) == null) {
        gameBoard(i)(state.oldCol) = if (state.turn == 1)
          new Piece("R", i, state.oldCol, 1)
        else
          new Piece("Y", i, state.oldCol, 0)
        println(i, state.oldCol)
        return new MoveValidation(new State(i, state.oldCol, 0, 0, 0), true)
      } else
        i += 1
    }
    new MoveValidation(null, false)
  }
}
