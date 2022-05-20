package ChessPieces

import GameEngines.Engines.ChessEngine
import javafx.scene.image.ImageView

abstract class Piece(pieceName: String, row: Int, col: Int, team: Int) {


  val name: String = pieceName
  var image: ImageView
  val x: Int = row
  val y: Int = col
  var firstMove: Boolean = true
  val color: Int = team

  protected def loadImage(): Unit = {
    image = new ImageView("Resources/Chess/" + name + ".png")
  }

  protected def canEat(atkX: Int, atkY: Int): Boolean = {
    if (color == ChessEngine.board(atkX)(atkY).color) return false
    true
  }

  def validateMove(newX: Int, newY: Int): Boolean = ???
}

object Piece {

}
