package Chess.Pieces

import Base.Piece

class Knight(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) return false

    if (!(((curRow == newRow + 2 && (curCol == newCol - 1 || curCol == newCol + 1)) ||
      (curRow == newRow + 1 && (curCol == newCol - 2 || curCol == newCol + 2)) ||
      (curRow == newRow - 1 && (curCol == newCol - 2 || curCol == newCol + 2)) ||
      (curRow == newRow - 2 && (curCol == newCol - 1 || curCol == newCol + 1))) &&
      canEat(board: Array[Array[Piece]], newRow, newCol)))
      return false

    true
  }
}
