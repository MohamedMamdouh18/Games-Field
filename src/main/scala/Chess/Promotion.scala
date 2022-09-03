package Chess

import Chess.Pieces.ChessPiece

trait Promotion {
  def promote(r: Int, c: Int): ChessPiece
}
