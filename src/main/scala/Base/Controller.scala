package Base

abstract class Controller {
  def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation

  def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int = 0, state: State = null): Boolean

  def createState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, newX: Int, newY: Int): Piece = {
    val removed = gameBoard(newX)(newY)

    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = null
    modifiedPiece.curRow = newX
    modifiedPiece.curCol = newY
    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = modifiedPiece

    removed
  }

  def restoreState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, removedPiece: Piece,
                   oldRow: Int, oldCol: Int, newRow: Int, newCol: Int): Unit = {
    gameBoard(oldRow)(oldCol) = modifiedPiece
    gameBoard(newRow)(newCol) = removedPiece

    modifiedPiece.curRow = oldRow
    modifiedPiece.curCol = oldCol
  }
}
