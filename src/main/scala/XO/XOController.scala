package XO

import Base.{Controller, MoveValidation, Piece, State}

class XOController extends Controller {

  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    if (gameBoard(state.oldRow)(state.oldCol) == null) {
      gameBoard(state.oldRow)(state.oldCol) = if (state.turn == 0)
        new Piece(XOEn.X, state.oldRow, state.oldCol, 0)
      else
        new Piece(XOEn.O, state.oldRow, state.oldCol, 1)

      new MoveValidation(state, true)
    } else
      new MoveValidation(state, false)
  }

  override def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    for (i <- 0 until 3) {
      if (gameBoard(i)(0) != null && gameBoard(i)(1) != null && gameBoard(i)(2) != null &&
        gameBoard(i)(0).color == gameBoard(i)(1).color && gameBoard(i)(0).color == gameBoard(i)(2).color) {
        return true
      }
    }

    for (i <- 0 until 3) {
      if (gameBoard(0)(i) != null && gameBoard(1)(i) != null && gameBoard(2)(i) != null &&
        gameBoard(0)(i).color == gameBoard(1)(i).color && gameBoard(0)(i).color == gameBoard(2)(i).color) {
        return true
      }
    }

    if (gameBoard(0)(0) != null && gameBoard(1)(1) != null && gameBoard(2)(2) != null &&
      gameBoard(0)(0).color == gameBoard(1)(1).color && gameBoard(0)(0).color == gameBoard(2)(2).color) {
      return true
    }

    if (gameBoard(0)(2) != null && gameBoard(1)(1) != null && gameBoard(2)(0) != null &&
      gameBoard(0)(2).color == gameBoard(1)(1).color && gameBoard(0)(2).color == gameBoard(2)(0).color) {
      return true
    }

    false
  }

  override def checkTie(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    for (i <- 0 until 3)
      for (j <- 0 until 3)
        if (gameBoard(i)(j) == null)
          return false

    true
  }
}
