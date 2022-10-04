package Base

import javafx.scene.image.ImageView

class Piece(pieceName: String, row: Int, col: Int, team: Int) {
  val color: Int = team
  var name: String = pieceName
  var curRow: Int = row
  var curCol: Int = col
  var image: ImageView = _
}