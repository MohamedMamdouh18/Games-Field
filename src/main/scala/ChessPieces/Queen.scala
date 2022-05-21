package ChessPieces

import javafx.scene.image.ImageView

class Queen(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newCol: Int, newRow: Int): Boolean = {
    if(color == 0){
      val king = new King(null, curRow, curCol, 0)
      val bishop = new Bishop(null, curRow, curCol, 0)
      val castle = new Castle(null, curRow, curCol, 0)

      king.validateMove(newCol , newRow) || bishop.validateMove(newCol , newRow) || castle.validateMove(newCol , newRow)
    }else{
      val king = new King(null, curRow, curCol, 1)
      val bishop = new Bishop(null, curRow, curCol, 1)
      val castle = new Castle(null, curRow, curCol, 1)

      king.validateMove(newCol , newRow) || bishop.validateMove(newCol , newRow) || castle.validateMove(newCol , newRow)
    }
  }
}
