package Chess

import Base.{Controller, MoveValidation, Piece, State}
import Chess.Pieces.{ChessPiece, Knight}

class ChessController extends Controller {
  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    val piece = gameBoard(state.oldRow)(state.oldCol).asInstanceOf[ChessPiece]

    if (piece.validateMove(gameBoard, state.newCol, state.newRow))
      new MoveValidation(null, true)
    else
      new MoveValidation(null, false)
  }
}
