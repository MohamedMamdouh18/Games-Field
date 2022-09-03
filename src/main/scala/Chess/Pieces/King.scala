package Chess.Pieces

import Base.Piece
import javafx.util.Pair

class King(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(1, 0, 0, -1, 1, 1, -1, -1)
  override val dy: Array[Int] = Array(0, -1, 1, 0, 1, -1, 1, -1)
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    loopTemplate(board, newX, newY, validateMoveImpl).valid
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    loopTemplate(board, 0, 0, validatedMovesImpl).validMoves
  }

  override protected def validateMoveImpl(board: Array[Array[Piece]], x: Int, y: Int, i: Int): Boolean = {
    val validNewX = curRow + dx(i)
    val validNewY = curCol + dy(i)

    if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7 &&
      canEat(board, validNewX, validNewY)) {

      if (validNewY == y && validNewX == x)
        moves.valid = true
      if (moves.valid)
        return moves.valid
    }
    moves.valid
  }

  override protected def validatedMovesImpl(board: Array[Array[Piece]], x: Int, y: Int, i: Int): Boolean = {
    val validNewX = curRow + dx(i)
    val validNewY = curCol + dy(i)

    if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7 &&
      canEat(board, validNewX, validNewY))
      moves.validMoves = moves.validMoves :+ new Pair[Int, Int](validNewX, validNewY)

    false
  }
}
