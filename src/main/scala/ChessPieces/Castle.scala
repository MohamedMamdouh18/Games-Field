package ChessPieces

import Controllers.ChessController
import javafx.scene.image.ImageView

class Castle(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) return false
    if (newCol == curCol) {

      if (curRow > newRow) {
        for (i <- (newRow + 1) until curRow) if (ChessController.board(i)(curCol) != null) return false
      } else {
        for (i <- (curRow + 1) until newRow) if (ChessController.board(i)(curCol) != null) return false
      }
      if (!canEat(newRow, newCol)) return false
    } else if (newRow == curRow) {

      if (curCol > newCol) {
        for (i <- (newCol + 1) until curCol) if (ChessController.board(curRow)(i) != null) return false
      } else {
        for (i <- (curCol+1) until newCol) if (ChessController.board(curRow)(i) != null) return false
      }

      if (!canEat(newRow, newCol)) return false
    } else return false
    return true
  }
}
