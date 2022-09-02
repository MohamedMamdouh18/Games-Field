package Chess.Pieces

import Base.Piece

class Castle(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0)
      return false
    if (newCol == curCol) {
      if (curRow > newRow) {
        for (i <- (newRow + 1) until curRow) if (board(i)(curCol) != null)
          return false
      } else {
        for (i <- (curRow + 1) until newRow) if (board(i)(curCol) != null)
          return false
      }
      if (!canEat(board: Array[Array[Piece]], newRow, newCol))
        return false
    } else if (newRow == curRow) {

      if (curCol > newCol) {
        for (i <- (newCol + 1) until curCol) if (board(curRow)(i) != null)
          return false
      } else {
        for (i <- (curCol + 1) until newCol) if (board(curRow)(i) != null)
          return false
      }

      if (!canEat(board: Array[Array[Piece]], newRow, newCol))
        return false
    } else
      return false
    true
  }
}
