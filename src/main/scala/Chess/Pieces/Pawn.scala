package Chess.Pieces

import Base.Piece

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

  override def validateMove(board: Array[Array[Piece]], newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) return false
    //black == 1
    if (color == 1) {
      if (promotedDone) {
        promotedMove(newCol, newRow)
      } else {
        if ((firstMove && newCol == curCol && newRow == curRow + 2 && board(newRow)(newCol) == null) ||
          (newCol == curCol && newRow == curRow + 1 && board(newRow)(newCol) == null) ||
          (newCol == curCol - 1 && newRow == curRow + 1 && board(newRow)(newCol) != null
            && board(newRow)(newCol).color == 0) ||
          (newCol == curCol + 1 && newRow == curRow + 1 && board(newRow)(newCol) != null
            && board(newRow)(newCol).color == 0)) {
          if (newRow == 7) {
            promotion = true
          }
          true
        }
        else false
      }
    }
    else {
      if (promotedDone) {
        promotedMove(newCol, newRow)
      } else {
        if ((firstMove && newCol == curCol && newRow == curRow - 2 && board(newRow)(newCol) == null) ||
          (newCol == curCol && newRow == curRow - 1 && board(newRow)(newCol) == null) ||
          (newCol == curCol - 1 && newRow == curRow - 1 && board(newRow)(newCol) != null
            && board(newRow)(newCol).color == 1) ||
          (newCol == curCol + 1 && newRow == curRow - 1 && board(newRow)(newCol) != null
            && board(newRow)(newCol).color == 1)) {
          if (newRow == 0) {
            promotion = true
          }
          true
        }
        else false
      }
    }
  }
}
