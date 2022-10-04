package Connect4

import Base.{Controller, MoveValidation, Piece, State}

object Connect4Controller extends Controller {
  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    var i = 0
    while (i < 6) {
      if (gameBoard(i)(state.oldCol) == null) {
        gameBoard(i)(state.oldCol) = if (state.turn == 1)
          new Piece(Connect4En.Red, i, state.oldCol, 1)
        else
          new Piece(Connect4En.Yellow, i, state.oldCol, 0)
        return new MoveValidation(new State(i, state.oldCol, 0, 0, 0), true)
      } else
        i += 1
    }
    new MoveValidation(null, false)
  }

  override def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    val turns: Array[String] = Array(Connect4En.Yellow, Connect4En.Red)
    val dx: Array[Int] = Array(1, 1, 0, 0, -1, -1, 1, -1)
    val dy: Array[Int] = Array(1, -1, 1, -1, -1, 1, 0, 0)

    gameBoard.foreach(_.foreach(piece => {
      if (piece != null && piece.name == turns(turn)) {
        for (i <- 0 until 8) {
          if (checkDirection(gameBoard, piece.curRow, piece.curCol, dx(i), dy(i)))
            return true
        }
      }
    }))
    false
  }

  override def checkTie(board: Array[Array[Piece]], turn: Int): Boolean = {
    board.foreach(_.foreach(piece => {
      if (piece == null) {
        return false
      }
    }))
    true
  }

  /**
   * Returns if for a given direction and cell in the game board if the player of the piece in the first cell has won the game -has three other pieces in that direction-.
   *
   * @param gameBoard the game board of the current game.
   * @param row       the row of the starting piece.
   * @param col       the column of the starting piece.
   * @param i         the parameter for the rows that we want to check
   * @param j         the parameter for the columns that we want to chcek
   * @return true if the player won the game and false otherwise.
   */
  private def checkDirection(gameBoard: Array[Array[Piece]], row: Int, col: Int, i: Int, j: Int): Boolean = {
    val turn = gameBoard(row)(col).name
    for (move <- 1 to 3) {
      val newRow = row + move * i
      val newCol = col + move * j
      if (newRow < 0 || newRow > 5 || newCol < 0 || newCol > 6
        || gameBoard(newRow)(newCol) == null || gameBoard(newRow)(newCol).name != turn) return false
    }
    true
  }

}
