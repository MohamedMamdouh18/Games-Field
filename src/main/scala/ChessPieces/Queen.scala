package ChessPieces

import javafx.scene.image.ImageView

class Queen(name : String , x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newX: Int, newY: Int): Boolean = {
    false
  }
}
