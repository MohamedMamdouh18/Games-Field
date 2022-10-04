package Chess

import Base.Piece
import Chess.Pieces.{ChessPiece, Knight, Queen}

class ChessEstimator {
  /**
   * Returns the estimated score for a given board depending on the existing pieces in the game and its places.
   *
   * @param board the given game board to calculate its score.
   * @return the estimated score for a given board depending on the existing pieces.
   */
  def estimate(board: Array[Array[Piece]]): Int = {
    var score: Int = 0
    for (i <- board.indices)
      for (j <- board(0).indices)
        if (board(i)(j) != null)
          score += getPieceRank(board(i)(j).asInstanceOf[ChessPiece], i, j)

    score
  }

  /**
   * Returns the score for a piece in the game board depending on its type and place in the board.
   *
   * @param piece the piece to calculate the score for.
   * @param i     the row that the piece exists in.
   * @param j     the column that the piece exists in.
   * @return the score for the specific piece int the game board.
   */
  private def getPieceRank(piece: ChessPiece, i: Int, j: Int): Int = {
    var score: Int = 0
    if (!piece.isInstanceOf[Queen] && !piece.isInstanceOf[Knight] && piece.color == ChessEn.Black)
      score = piece.evaluationMatrix.reverse(i)(j).toInt
    else
      score = piece.evaluationMatrix(i)(j).toInt

    if (piece.color == ChessEn.Black) -1 * (piece.rank + score * 10) else piece.rank + score * 10
  }
}
