package Chess.Pieces

import Base.Piece

import scala.math.abs

class Bishop(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0 || !(abs(curCol - newCol) == abs(curRow - newRow))) return false
    if (newCol > curCol) {
      if (newRow > curRow) {
        for (i <- 1 until abs(curCol - newCol)) {
          if (board(curRow + i)(curCol + i) != null)
            return false
        }
      }
      else {
        for (i <- 1 until abs(curCol - newCol)) {
          if (board(curRow - i)(curCol + i) != null)
            return false
        }
      }
    }
    else {
      if (newRow > curRow) {
        for (i <- 1 until abs(curCol - newCol)) {
          if (board(curRow + i)(curCol - i) != null)
            return false
        }
      }
      else {
        for (i <- 1 until abs(curCol - newCol)) {
          if (board(curRow - i)(curCol - i) != null)
            return false
        }
      }
    }

    if (!canEat(board: Array[Array[Piece]], newRow, newCol))
      false
    else
      true
  }
}
