package Chess.Pieces

import Base.Piece
import javafx.scene.image.ImageView

class ChessPiece(pieceName: String, row: Int, col: Int, color: Int) extends Piece(pieceName, row, col, color) {
  var firstMove: Boolean = true

  def loadImage(): Unit = {
    if (name != null)
      image = new ImageView("Resources/Chess/" + name + ".png")
  }

  def wantPromote(): Boolean = {
    false
  }

  def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    false
  }

  def canEat(board: Array[Array[Piece]], atkRow: Int, atkCol: Int): Boolean = {
    if (board(atkRow)(atkCol) != null && color == board(atkRow)(atkCol).color)
      false
    else
      true
  }
}
