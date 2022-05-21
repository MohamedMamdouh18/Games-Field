package ChessPieces

import GameEngines.GamesControllers.ChessController
import javafx.scene.image.ImageView

import scala.math.abs

class Bishop(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0 || !(abs(curCol - newCol) == abs(curRow - newRow))) return false
    if (newCol > curCol) {
      if (newRow > curRow) {
        for (i <- 1 until abs(curCol - newCol)) {
          if (ChessController.board(curRow + i)(curCol + i) != null) return false
        }
      }
      else {
        for (i <- 1 until abs(curCol - newCol)) {
          if (ChessController.board(curRow - i)(curCol + i) != null) return false
        }
      }
    }
    else {
      if (newRow > curRow) {
        for (i <- 1 until abs(curCol - newCol)) {
          if (ChessController.board(curRow + i)(curCol - i) != null) return false
        }
      }
      else {
        for (i <- 1 until abs(curCol - newCol)) {
          if (ChessController.board(curRow - i)(curCol - i) != null) return false
        }
      }
    }

    if (!canEat(newRow, newCol)) false
    else true
  }
}

