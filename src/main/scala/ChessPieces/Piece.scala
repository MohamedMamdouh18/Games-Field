package ChessPieces

import GameEngines.Engines.ChessEngine
import GameEngines.GamesControllers.ChessController
import javafx.scene.image.ImageView

abstract class Piece(pieceName: String, row: Int, col: Int, team: Int) {
  var name: String = pieceName
  var curRow: Int = row
  var curCol: Int = col
  val color: Int = team
  var image: ImageView
  var firstMove: Boolean = true

  def validateMove(newX: Int, newY: Int): Boolean = ???

  def wantPromote() : Boolean ={
    false
  }

   def loadImage(): Unit = {
    if(name != null) image = new ImageView("Resources/Chess/" + name + ".png")
  }

  protected def canEat(atkRow: Int, atkCol: Int): Boolean = {
    if (ChessController.board(atkRow)(atkCol) != null && color == ChessController.board(atkRow)(atkCol).color) return false
    else true
  }
}

object Piece {

}
