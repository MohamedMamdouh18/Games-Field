package Chess

import Base.Piece
import Chess.Pieces.{ChessPiece, Knight, Queen}

class ChessEstimator {
  def estimate(board: Array[Array[Piece]]): Int = {
    var score: Int = 0
    for (i <- board.indices)
      for (j <- board(0).indices)
        if (board(i)(j) != null)
          score += getPieceRank(board(i)(j).asInstanceOf[ChessPiece], i, j)

    score
  }

  private def getPieceRank(piece: ChessPiece, i: Int, j: Int): Int = {
    var score: Int = 0
    if (!piece.isInstanceOf[Queen] && !piece.isInstanceOf[Knight] && piece.color == ChessEn.Black)
      score = piece.evaluationMatrix.reverse(i)(j).toInt
    else
      score = piece.evaluationMatrix(i)(j).toInt

    if (piece.color == ChessEn.Black) -1 * (piece.rank + score * 10) else piece.rank + score * 10
  }
}
