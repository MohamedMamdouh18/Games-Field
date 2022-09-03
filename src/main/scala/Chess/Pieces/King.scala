package Chess.Pieces

import Base.{Piece, State}
import javafx.util.Pair

class King(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(1, 0, 0, -1, 1, 1, -1, -1)
  override val dy: Array[Int] = Array(0, -1, 1, 0, 1, -1, 1, -1)
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    loopTemplate(board, newX, newY, validateMoveImpl).valid
  }

  override def validatedMoves(board: Array[Array[Piece]], newX: Int, newY: Int): Array[Pair[Int, Int]] = {
    clear()
    loopTemplate(board, newX, newY, validatedMovesImpl).validMoves
  }

  override protected def validateMoveImpl(board: Array[Array[Piece]], s: State): Unit = {
    if (s.oldCol == s.newCol && s.oldRow == s.newRow)
      moves.valid = true
  }

  override protected def validatedMovesImpl(board: Array[Array[Piece]], s: State): Unit = {
    moves.validMoves = moves.validMoves :+ new Pair[Int, Int](s.oldRow, s.oldCol)
  }
}
