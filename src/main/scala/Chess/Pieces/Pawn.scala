package Chess.Pieces

import Base.Piece
import javafx.util.Pair

class Pawn(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array()
  override val dy: Array[Int] = Array()
  override var rank: Int = 10
  var promotedDone: Boolean = false
  var promotion: Boolean = false
  var promotedMove: (Int, Int) => Boolean = _
  loadImage()

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

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    var direction = color
    if (direction == 0) direction = -1

    var validMoves: Array[Pair[Int, Int]] = Array()
    if (curRow + direction >= 0 && curRow + direction <= 7 && board(curRow + direction)(curCol) == null)
      validMoves = validMoves :+ new Pair[Int, Int](curRow + direction, curCol)

    if (curRow + direction >= 0 && curRow + direction <= 7 && curCol + 1 <= 7 &&
      board(curRow + direction)(curCol + 1) != null && board(curRow + direction)(curCol + 1).color != color)
      validMoves = validMoves :+ new Pair[Int, Int](curRow + direction, curCol + 1)

    if (curRow + direction >= 0 && curRow + direction <= 7 && curCol - 1 >= 0 &&
      board(curRow + direction)(curCol - 1) != null && board(curRow + direction)(curCol - 1).color != color)
      validMoves = validMoves :+ new Pair[Int, Int](curRow + direction, curCol - 1)

    if (curRow + direction * 2 >= 0 && curRow + direction * 2 <= 7 && firstMove &&
      board(curRow + direction * 2)(curCol) == null && board(curRow + direction)(curCol) == null)
      validMoves = validMoves :+ new Pair[Int, Int](curRow + direction * 2, curCol)

    validMoves
  }

  override def clone(): ChessPiece = {
    val x = new Pawn(name, curRow, curCol, color)
    x
  }
}
