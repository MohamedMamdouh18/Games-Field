package Chess.Pieces

import Base.Piece
import javafx.util.Pair

import scala.math.max

class Rook(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(1, 0, 0, -1)
  override val dy: Array[Int] = Array(0, -1, 1, 0)
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    loopTemplate(board, newX, newY, validateMoveImpl,
      if (curRow == newX) max(curCol, newY) else max(curRow, newX)).valid
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    loopTemplate(board, 0, 0, validatedMovesImpl, 7).validMoves
  }

  override def clone(): ChessPiece = {
    val x = new Rook(name, curRow, curCol, color)
    x
  }
}
