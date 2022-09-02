package Chess.Pieces

import Base.Piece

class Queen(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newCol: Int, newRow: Int): Boolean = {
    if (color == 0) {
      val king = new King(null, curRow, curCol, 0)
      val bishop = new Bishop(null, curRow, curCol, 0)
      val castle = new Castle(null, curRow, curCol, 0)

      king.validateMove(board: Array[Array[Piece]], newCol, newRow) ||
        bishop.validateMove(board: Array[Array[Piece]], newCol, newRow) ||
        castle.validateMove(board: Array[Array[Piece]], newCol, newRow)
    } else {
      val king = new King(null, curRow, curCol, 1)
      val bishop = new Bishop(null, curRow, curCol, 1)
      val castle = new Castle(null, curRow, curCol, 1)

      king.validateMove(board: Array[Array[Piece]], newCol, newRow) ||
        bishop.validateMove(board: Array[Array[Piece]], newCol, newRow) ||
        castle.validateMove(board: Array[Array[Piece]], newCol, newRow)
    }
  }
}
