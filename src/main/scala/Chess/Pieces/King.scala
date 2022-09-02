package Chess.Pieces

import Base.Piece

class King(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) return false

    if( !(((newCol == curCol + 1 && (newRow == curRow || newRow == curRow - 1  || newRow == curRow + 1))
      || (newCol == curCol - 1 && (newRow == curRow || newRow == curRow - 1  || newRow == curRow + 1))
      || (newCol == curCol && (newRow == curRow - 1  || newRow == curRow + 1))) &&
      canEat(board: Array[Array[Piece]], newRow , newCol)) )
      return false

    true
  }
}
