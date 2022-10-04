package Base

trait Controller {
  /***
   * Returns an object that holds two values the whole state and true if the move is valid.
   * @param gameBoard the board at the current state of the game.
   * @param state object that holds the current state (if exists) and the new state.
   * @return an object that holds a state and true if the move is valid.
   */
  def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation

  /***
   * Returns ture if the game has ended.
   * @param gameBoard the board at the current state of the game.
   * @param turn identifies who will play.
   * @return ture if the game has ended.
   */
  def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int = -1): Boolean

  /***
   * Returns ture if the game has ended as tie.
   * @param gameBoard the board at the current state of the game.
   * @param turn identifies who will play.
   * @return ture if the game has ended as tie.
   */
  def checkTie(gameBoard: Array[Array[Piece]], turn: Int = -1): Boolean

  /***
   * Returns a piece object if a piece is removed.
   * @param gameBoard the board at the current state of the game.
   * @param modifiedPiece the piece which will move.
   * @param newX the new x coordinate for the piece.
   * @param newY the new y coordinate for the piece.
   * @return a piece object if a piece is removed, null otherwise.
   */
  def createState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, newX: Int, newY: Int): Piece = {
    val removed = gameBoard(newX)(newY)

    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = null
    modifiedPiece.curRow = newX
    modifiedPiece.curCol = newY
    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = modifiedPiece

    removed
  }

  /***
   * Restores the board to its previous state.
   * @param gameBoard the board at the current state of the game.
   * @param modifiedPiece the piece that will return to its previous state.
   * @param removedPiece a removed piece that will return to its previous state (if exists).
   * @param state object that holds the current state (if exists) and the new state.
   */
  def restoreState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, removedPiece: Piece, state: State): Unit = {
    gameBoard(state.oldRow)(state.oldCol) = modifiedPiece
    gameBoard(state.newRow)(state.newCol) = removedPiece

    modifiedPiece.curRow = state.oldRow
    modifiedPiece.curCol = state.oldCol
  }
}
