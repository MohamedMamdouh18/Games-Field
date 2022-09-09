package Chess.Pieces

import Base.{Piece, State}
import Chess.ChessController
import javafx.util.Pair

class King(name: String, x: Int, y: Int, color: Int) extends ChessPiece(name, x, y, color) {
  override val dx: Array[Int] = Array(1, 0, 0, -1, 1, 1, -1, -1)
  override val dy: Array[Int] = Array(0, -1, 1, 0, 1, -1, 1, -1)
  override var rank: Int = 900
  loadImage()

  override def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean = {
    clear()
    castling(board)

    moves.validMoves.foreach(move => {
      castled = validateMoveImpl(new State(newX, newY, move.getKey, move.getValue, -1))
      castled
    })

    loopTemplate(board, newX, newY, validateMoveImpl, 1).valid
  }

  override def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]] = {
    clear()
    castling(board)
    loopTemplate(board, 0, 0, validatedMovesImpl, 1).validMoves
  }

  override def clone(): ChessPiece = {
    val x = new King(name, curRow, curCol, color)
    x
  }

  private def castling(board: Array[Array[Piece]]): Unit = {
    val rightRook = board(7 * (1 - color))(7).asInstanceOf[Rook]
    val leftRook = board(7 * (1 - color))(0).asInstanceOf[Rook]

    if (this.firstMove && !this.checked) {
      val controller = new ChessController
      checkCastling(controller, rightRook, board, 5, 6)
      checkCastling(controller, leftRook, board, 3, 2)
    }
  }

  private def checkCastling(controller: ChessController, rook: Rook, board: Array[Array[Piece]],
                            c1: Int, c2: Int): Unit = {
    if (rook != null && rook.isInstanceOf[Rook] && rook.firstMove
      && board(7 * (1 - color))(c1) == null && board(7 * (1 - color))(c2) == null) {
      val firstPassedCell = controller.checkMate(board, color, 7 * (1 - color), c1)
      val secondPassedCell = controller.checkMate(board, color, 7 * (1 - color), c2)
      if (!firstPassedCell && !secondPassedCell)
        moves.validMoves = moves.validMoves :+ new Pair[Int, Int](7 * (1 - color), c2)
    }
  }
}
