package Chess

import Chess.Pieces.ChessPiece

trait Promotion {
  def promote(): ChessPiece
}
