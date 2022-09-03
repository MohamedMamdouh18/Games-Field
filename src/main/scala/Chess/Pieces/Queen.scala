package Chess.Pieces

import Base.{Piece, State}
import javafx.util.Pair

class Queen(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  var b = new Bishop(null, curRow, curCol, color)
  var c = new Castle(null, curRow, curCol, color)
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    updatePos()
    moves.valid = b.validateMove(board, newX, newY) || c.validateMove(board, newX, newY)
    moves.valid
  }

  override def validatedMoves(board: Array[Array[Piece]], newX: Int, newY: Int): Array[Pair[Int, Int]] = {
    clear()
    updatePos()
    moves.validMoves = b.validatedMoves(board, newX, newY) ++ c.validatedMoves(board, newX, newY)
    moves.validMoves
  }

  override protected def loopTemplate(board: Array[Array[Piece]], newX: Int, newY: Int,
                                      execute: (Array[Array[Piece]], State) => Unit): Moves = {
    moves
  }

  override protected def validateMoveImpl(board: Array[Array[Piece]], s: State): Unit = {

  }

  override protected def validatedMovesImpl(board: Array[Array[Piece]], s: State): Unit = {

  }

  private def updatePos(): Unit = {
    b.curRow = curRow
    b.curCol = curCol
    c.curRow = curRow
    c.curCol = curCol
  }

  override val dx: Array[Int] = Array()
  override val dy: Array[Int] = Array()
}
