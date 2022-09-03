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
    loopTemplate(board, newX, newY, validateMoveImpl).valid
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    loopTemplate(board, 0, 0, validatedMovesImpl).validMoves
  }

  override protected def validateMoveImpl(board: Array[Array[Piece]], x: Int, y: Int, i: Int): Boolean = {
    for (j <- 1 to abs(curCol - y)) {
      val validNewX = curRow + dx(i) * j
      val validNewY = curCol + dy(i) * j

      if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7 &&
        canEat(board, validNewX, validNewY)) {

        if (validNewY == y && validNewX == x)
          moves.valid = true
        if (moves.valid)
          return moves.valid
      }
    }
    moves.valid
  }

  override protected def validatedMovesImpl(board: Array[Array[Piece]], x: Int, y: Int, i: Int): Boolean = {
    for (j <- 1 to abs(curCol - y)) {
      val validNewX = curRow + dx(i) * j
      val validNewY = curCol + dy(i) * j

      if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7 &&
        canEat(board, validNewX, validNewY))
        moves.validMoves = moves.validMoves :+ new Pair[Int, Int](validNewX, validNewY)
    }
    false
  }
}
