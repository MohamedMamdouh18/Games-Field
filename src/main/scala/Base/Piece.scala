package Base

import javafx.scene.image.ImageView

class Piece(pieceName: String, row: Int, col: Int, team: Int) {
  var name: String = pieceName
  var curRow: Int = row
  var curCol: Int = col
  var firstMove: Boolean = true
  val color: Int = team
  var image: ImageView = _

  def loadImage(): Unit = {
    if (name != null)
      image = new ImageView("Resources/Chess/" + name + ".png")
  }
}

