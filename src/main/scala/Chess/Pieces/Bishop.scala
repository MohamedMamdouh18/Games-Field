package Chess.Pieces

import Base.Piece
import javafx.util.Pair

import scala.math.abs

class Bishop(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(1, 1, -1, -1)
  override val dy: Array[Int] = Array(1, -1, 1, -1)
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    if (!(abs(curCol - newY) == abs(curRow - newX)))
      return false
    loopTemplate(board, newX, newY, validateMoveImpl, abs(curCol - newY)).valid
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    loopTemplate(board, 0, 0, validatedMovesImpl, 7).validMoves
  }

  override def clone(): ChessPiece = {
    val x = new Bishop(name, curRow, curCol, color)
    x
  }
}
