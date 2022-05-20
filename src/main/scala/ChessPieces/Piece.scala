package ChessPieces

import GameEngines.Engines.ChessEngine
import javafx.scene.image.ImageView

abstract class Piece(pieceName: String, row: Int, col: Int, team: Int) {
  val name: String = pieceName
  val x: Int = row
  val y: Int = col
  val color: Int = team
  var image: ImageView
  var firstMove: Boolean = true

  def validateMove(newX: Int, newY: Int): Boolean = ???

  protected def loadImage(): Unit = {
    image = new ImageView("Resources/Chess/" + name + ".png")
  }

  protected def canEat(atkX: Int, atkY: Int): Boolean = {
    if (color == ChessEngine.board(atkX)(atkY).color) return false
    true
  }
}

object Piece {

}
