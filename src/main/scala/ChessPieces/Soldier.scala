package ChessPieces

import GameEngines.Engines.ChessEngine
import GameEngines.GamesControllers.ChessController
import javafx.scene.image.ImageView

class Soldier(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()

  override def validateMove(newCol: Int, newRow: Int): Boolean = {
    //black == 1
    if (color == 1) {
      if (curRow + 2 < 8 && firstMove && newCol == curCol && newRow == curRow + 2 && ChessController.board(newRow)(newCol) == null)  true
      else if (curRow + 1 < 8 && newCol == curCol && newRow == curRow + 1 && ChessController.board(newRow)(newCol) == null)  true
      else if (curRow + 1 < 8 && curCol - 1 >= 0 && newCol == curCol - 1 && newRow == curRow + 1&& ChessController.board(newRow)(newCol) != null
        && ChessController.board(newRow)(newCol).color == 0)  true
      else if (curRow + 1 < 8 && curCol + 1 < 8 && newCol == curCol + 1 && newRow == curRow + 1&& ChessController.board(newRow)(newCol) != null
        && ChessController.board(newRow)(newCol).color == 0)  true
      else false

    }
    //white == 0
    else {
      if (curRow - 2 >= 0 && firstMove && newCol == curCol && newRow == curRow - 2 && ChessController.board(newRow)(newCol) == null) true
      else if (curRow - 1 >= 0 && newCol == curCol && newRow == curRow - 1 && ChessController.board(newRow)(newCol) == null)  true
      else if (curRow - 1 >= 0 && curCol - 1 >= 0 && newCol == curCol - 1 && newRow == curRow - 1&& ChessController.board(newRow)(newCol) != null
        && ChessController.board(newRow)(newCol).color == 1)  true
      else if (curRow - 1 >= 0 && curCol + 1 < 8 && newCol == curCol + 1 && newRow == curRow - 1 && ChessController.board(newRow)(newCol) != null
        && ChessController.board(newRow)(newCol).color == 1)  true
      else false
    }
  }
}
