package Base

trait Controller {
  def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation

  def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int = 0): Boolean

  def checkTie(gameBoard: Array[Array[Piece]], turn: Int = 0): Boolean

  def createState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, newX: Int, newY: Int): Piece = {
    val removed = gameBoard(newX)(newY)

    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = null
    modifiedPiece.curRow = newX
    modifiedPiece.curCol = newY
    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = modifiedPiece

    removed
  }

  def restoreState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, removedPiece: Piece, state: State): Unit = {
    gameBoard(state.oldRow)(state.oldCol) = modifiedPiece
    gameBoard(state.newRow)(state.newCol) = removedPiece

    modifiedPiece.curRow = state.oldRow
    modifiedPiece.curCol = state.oldCol
  }
}
