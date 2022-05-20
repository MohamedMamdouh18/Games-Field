package ChessPieces

import GameEngines.Engines.ChessEngine
import javafx.scene.image.ImageView

class Soldier(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newX: Int, newY: Int): Boolean = {
    if (color == 1) {
      if (y + 2 < 8 && firstMove && newX == x && newY == y + 2 && ChessEngine.board(newX)(newY).color == 0) return true
      if (y + 1 < 8 && newX == x && newY == y + 1 && ChessEngine.board(newX)(newY).color == 0) return true
      if (y + 1 < 8 && x - 1 >= 0 && newX == x - 1 && newY == y + 1 && ChessEngine.board(newX)(newY).color == -1) return true
      if (y + 1 < 8 && x + 1 < 8 && newX == x + 1 && newY == y + 1 && ChessEngine.board(newX)(newY).color == -1) return true
      false
    } else {
      if (y - 2 < 8 && firstMove && newX == x && newY == y - 2 && ChessEngine.board(newX)(newY).color == 0) return true
      if (y - 1 < 8 && newX == x && newY == y - 1 && ChessEngine.board(newX)(newY).color == 0) return true
      if (y - 1 < 8 && x - 1 >= 0 && newX == x - 1 && newY == y - 1 && ChessEngine.board(newX)(newY).color == 1) return true
      if (y - 1 < 8 && x + 1 < 8 && newX == x + 1 && newY == y - 1 && ChessEngine.board(newX)(newY).color == 1) return true
      false
    }
  }
}
