package Chess

import Base.Piece
import Chess.Pieces.ChessPiece

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
    if (piece.color == ChessEn.Black) piece.rank + piece.evaluationMatrix.reverse(i)(j).toInt * 10
    else piece.rank + piece.evaluationMatrix(i)(j).toInt * 10
  }
}
