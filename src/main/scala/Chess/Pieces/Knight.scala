package Chess.Pieces

import Base.Piece
import javafx.util.Pair

class Knight(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(2, 1, -1, 2, -1, -2, 1, -2)
  override val dy: Array[Int] = Array(1, 2, 2, -1, -2, -1, -2, 1)
  override var rank: Int = 30
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    loopTemplate(board, newX, newY, validateMoveImpl, 1).valid
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    loopTemplate(board, 0, 0, validatedMovesImpl, 1).validMoves
  }

  override def clone(): ChessPiece = {
    val x = new Knight(name, curRow, curCol, color)
    x
  }
}
