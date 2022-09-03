package Chess.Pieces

import Base.{Piece, State}
import javafx.util.Pair

class Pawn(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  loadImage()
  var promotedDone: Boolean = false
  var promotion: Boolean = false
  var promotedMove: (Int, Int) => Boolean = _

  override def wantPromote(): Boolean = {
    if (promotion) {
      promotion = false
      return true
    }
    false
  }

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    if (newY > 7 || newY < 0 || newX > 7 || newX < 0) return false
    if (color == 1) {
      if (promotedDone) {
        promotedMove(newY, newX)
      } else {
        if ((firstMove && newY == curCol && newX == curRow + 2 && board(newX)(newY) == null) ||
          (newY == curCol && newX == curRow + 1 && board(newX)(newY) == null) ||
          (newY == curCol - 1 && newX == curRow + 1 && board(newX)(newY) != null
            && board(newX)(newY).color == 0) ||
          (newY == curCol + 1 && newX == curRow + 1 && board(newX)(newY) != null
            && board(newX)(newY).color == 0)) {
          if (newX == 7) {
            promotion = true
          }
          true
        }
        else false
      }
    }
    else {
      if (promotedDone) {
        promotedMove(newY, newX)
      } else {
        if ((firstMove && newY == curCol && newX == curRow - 2 && board(newX)(newY) == null) ||
          (newY == curCol && newX == curRow - 1 && board(newX)(newY) == null) ||
          (newY == curCol - 1 && newX == curRow - 1 && board(newX)(newY) != null
            && board(newX)(newY).color == 1) ||
          (newY == curCol + 1 && newX == curRow - 1 && board(newX)(newY) != null
            && board(newX)(newY).color == 1)) {
          if (newX == 0) {
            promotion = true
          }
          true
        }
        else false
      }
    }
  }

  override def validatedMoves(board: Array[Array[Piece]], newX: Int, newY: Int): Array[Pair[Int, Int]] = {
    moves.validMoves
  }

  override protected def validateMoveImpl(board: Array[Array[Piece]], s: State): Unit = {

  }

  override protected def validatedMovesImpl(board: Array[Array[Piece]], s: State): Unit = {

  }

  override protected def loopTemplate(board: Array[Array[Piece]], newX: Int, newY: Int,
                                      execute: (Array[Array[Piece]], State) => Unit): Moves = {
    moves
  }

  override val dx: Array[Int] = Array()
  override val dy: Array[Int] = Array()
}
