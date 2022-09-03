package Chess.Pieces

import Base.{Piece, State}
import javafx.util.Pair

import scala.math.abs

class Bishop(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(1, 1, -1, -1)
  override val dy: Array[Int] = Array(1, -1, 1, -1)
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    loopTemplate(board, newX, newY, validateMoveImpl).valid
  }

  override def validatedMoves(board: Array[Array[Piece]], newX: Int, newY: Int): Array[Pair[Int, Int]] = {
    clear()
    loopTemplate(board, newX, newY, validatedMovesImpl).validMoves
  }

  override protected def loopTemplate(board: Array[Array[Piece]], newX: Int, newY: Int,
                                      execute: (Array[Array[Piece]], State) => Unit): Moves = {
    if (newY > 7 || newY < 0 || newX > 7 || newX < 0 || !(abs(curCol - newY) == abs(curRow - newX)))
      return moves

    var validNewX, validNewY: Int = 0

    for (i <- 0 to 3) {
      for (j <- 1 to abs(curCol - newY)) {
        validNewX = curRow + dx(i) * j
        validNewY = curCol + dy(i) * j

        if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7 &&
          canEat(board, validNewX, validNewY)) {
          val s: State = new State(validNewX, validNewY, newX, newY, 0)

          execute(board, s)
          if (moves.valid)
            return moves
        }
      }
    }
    moves
  }

  override protected def validateMoveImpl(board: Array[Array[Piece]], s: State): Unit = {
    if (s.oldCol == s.newCol && s.oldRow == s.newRow)
      moves.valid = true
  }

  override protected def validatedMovesImpl(board: Array[Array[Piece]], s: State): Unit = {
    moves.validMoves = moves.validMoves :+ new Pair[Int, Int](s.oldRow, s.oldCol)
  }
}
