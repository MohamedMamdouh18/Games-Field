package ChessPieces

import javafx.scene.image.ImageView

class Knight(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) return false

    if(!(((curRow == newRow + 2&&(curCol == newCol - 1 || curCol == newCol + 1)) ||
      (curRow == newRow + 1&&(curCol == newCol - 2 || curCol == newCol + 2)) ||
      (curRow == newRow - 1&&(curCol == newCol - 2 || curCol == newCol + 2)) ||
      (curRow == newRow - 2&&(curCol == newCol - 1 || curCol == newCol + 1))) && canEat(newRow , newCol))) return false

    true
  }
}
