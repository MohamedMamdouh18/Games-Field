package ChessPieces

import GameEngines.Engines.ChessEngine
import javafx.scene.image.ImageView

class Castle(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newX: Int, newY: Int): Boolean = {
    if (newX > 7 || newX < 0 || newY > 7 || newY < 0) return false
    if (newX == x) {
      for (i <- y until newY) {
        if (ChessEngine.board(x)(i) != null) return false
      }
      if (!canEat(newX, newY)) return false
    } else if (newY == y) {
      for (i <- x until newX) {
        if (ChessEngine.board(i)(y) != null) return false
      }
      if (!canEat(newX, newY)) return false
    } else return false
    true
  }
}
