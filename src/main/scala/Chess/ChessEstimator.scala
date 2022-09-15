package Chess

import Base.Piece
import Chess.Pieces.{ChessPiece, Queen}

class ChessEstimator {
  def estimate(board: Array[Array[Piece]]): Int = {
    val score: Array[Int] = Array(0, 0)
    for (i <- board.indices)
      for (j <- board(0).indices)
        if (board(i)(j) != null)
          score(board(i)(j).color) += getPieceRank(board(i)(j).asInstanceOf[ChessPiece], i, j)

    score(0) - score(1)
  }

  private def getPieceRank(piece: ChessPiece, i: Int, j: Int): Int = {
    var score: Int = 0
    if (!piece.isInstanceOf[Queen] && piece.color == ChessEn.Black)
      score = piece.evaluationMatrix.reverse(i)(j).toInt
    else
      score = piece.evaluationMatrix(i)(j).toInt

    piece.rank + score * 10
  }
}
