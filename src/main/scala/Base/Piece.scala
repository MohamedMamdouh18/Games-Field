package Base

import javafx.scene.image.ImageView

class Piece(pieceName: String, row: Int, col: Int, team: Int){
  var name: String = pieceName
  var curRow: Int = row
  var curCol: Int = col
  val color: Int = team
  var image: ImageView = _

  override def clone(): Piece = ???
}

