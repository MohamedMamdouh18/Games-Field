package Chess.Pieces

import Base.{Piece, State}
import Chess.ChessController
import javafx.util.Pair

class King(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(1, 0, 0, -1, 1, 1, -1, -1)
  override val dy: Array[Int] = Array(0, -1, 1, 0, 1, -1, 1, -1)
  override var rank: Int = 900
  override var evaluationMatrix: Array[Array[Double]] = Array(
    Array(-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0),
    Array(-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0),
    Array(-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0),
    Array(-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0),
    Array(-2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0),
    Array(-1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0),
    Array(2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0),
    Array(2.0, 3.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0)
  )

  override def wantCastle(oldCol: Int, newCol: Int): Boolean = {
    Math.abs(oldCol - newCol) == 2 && firstMove
  }

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    castling(board)

    moves.validMoves.foreach(move => {
      validateMoveImpl(new State(newX, newY, move.getKey, move.getValue, -1))
      if (moves.valid)
        return moves.valid
    })

    loopTemplate(board, newX, newY, validateMoveImpl, 1).valid
  }

  /**
   * Starts checking for castling if it is possible.
   * @param board the game board which has been played so far.
   */
  private def castling(board: Array[Array[Piece]]): Unit = {
    val rightRook = board(7 * (1 - color))(7)
    val leftRook = board(7 * (1 - color))(0)

    if (this.firstMove && !this.checked) {
      checkCastling(rightRook, board, 5, 6)
      checkCastling(leftRook, board, 3, 2)
    }
  }

  /**
   * Adds castling move to moves array if it is valid.
   * @param rook the rook to do castle with it.
   * @param board the game board which has been played so far.
   * @param c1 first column after the rook.
   * @param c2 second column after the rook.
   */
  private def checkCastling(rook: Piece, board: Array[Array[Piece]],
                            c1: Int, c2: Int): Unit = {
    if (rook != null && rook.isInstanceOf[Rook] && rook.asInstanceOf[ChessPiece].firstMove
      && board(7 * (1 - color))(c1) == null && board(7 * (1 - color))(c2) == null) {
      val firstPassedCell = ChessController.checkMate(board, color, 7 * (1 - color), c1)
      val secondPassedCell = ChessController.checkMate(board, color, 7 * (1 - color), c2)
      if (!firstPassedCell && !secondPassedCell)
        moves.validMoves = moves.validMoves :+ new Pair[Int, Int](7 * (1 - color), c2)
    }
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    castling(board)
    loopTemplate(board, 0, 0, validatedMovesImpl, 1).validMoves
  }
}
