package ChessPieces

import GameEngines.Engines.ChessEngine
import GameEngines.GamesControllers.ChessController
import javafx.scene.image.ImageView

class Soldier(name: String, x: Int, y: Int, color: Int) extends Piece(name, x, y, color) {
  override var image: ImageView = _
  loadImage()
  var promotedDone : Boolean = false
  var promotion : Boolean = false
  var promotedMove: (Int ,Int) => Boolean = null

  override def wantPromote(): Boolean = {
    if(promotion){
      promotion = false
      return true
    }
    false
  }

  override def validateMove(newCol: Int, newRow: Int): Boolean = {
    if (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) return false
    //black == 1
    if (color == 1) {
      if(promotedDone){
        return promotedMove(newCol , newRow)
      }else{
        if ((firstMove && newCol == curCol && newRow == curRow + 2 && ChessController.board(newRow)(newCol) == null) ||
          (newCol == curCol && newRow == curRow + 1 && ChessController.board(newRow)(newCol) == null) ||
          (newCol == curCol - 1 && newRow == curRow + 1&& ChessController.board(newRow)(newCol) != null
          && ChessController.board(newRow)(newCol).color == 0) ||
          ( newCol == curCol + 1 && newRow == curRow + 1&& ChessController.board(newRow)(newCol) != null
          && ChessController.board(newRow)(newCol).color == 0))  {
          if(newRow == 7){
            promotion = true
          }
          true
        }
        else false
      }
    }
    //white == 0
    else {
      if(promotedDone){
        return promotedMove(newCol , newRow)
      }else{
        if ((firstMove && newCol == curCol && newRow == curRow - 2 && ChessController.board(newRow)(newCol) == null) ||
          (newCol == curCol && newRow == curRow - 1 && ChessController.board(newRow)(newCol) == null)  ||
          (newCol == curCol - 1 && newRow == curRow - 1&& ChessController.board(newRow)(newCol) != null
          && ChessController.board(newRow)(newCol).color == 1)  ||
          (newCol == curCol + 1 && newRow == curRow - 1 && ChessController.board(newRow)(newCol) != null
          && ChessController.board(newRow)(newCol).color == 1))  {
          if(newRow == 0){
            promotion = true
          }
          true
        }
        else false
      }
    }
  }
}
