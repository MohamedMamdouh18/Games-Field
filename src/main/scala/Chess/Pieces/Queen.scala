package Chess.Pieces

import Base.Piece
import javafx.util.Pair

class Queen(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array()
  override val dy: Array[Int] = Array()
  loadImage()
  var b = new Bishop(null, curRow, curCol, color)
  var c = new Castle(null, curRow, curCol, color)

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    updatePos()
    moves.valid = b.validateMove(board, newX, newY) || c.validateMove(board, newX, newY)
    moves.valid
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    updatePos()
    moves.validMoves = b.validatedMoves(board) ++ c.validatedMoves(board)
    moves.validMoves
  }

  private def updatePos(): Unit = {
    b.curRow = curRow
    b.curCol = curCol
    c.curRow = curRow
    c.curCol = curCol
  }

  override def clone(): ChessPiece = {
    val x = new Queen(name, curRow, curCol, color)
    x
  }
}
