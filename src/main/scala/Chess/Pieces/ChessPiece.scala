package Chess.Pieces

import Base.Piece
import javafx.scene.image.ImageView
import javafx.util.Pair

abstract class ChessPiece(pieceName: String, row: Int, col: Int, color: Int) extends Piece(pieceName, row, col, color) {
  var firstMove: Boolean = true
  var moves: Moves = new Moves
  val dx: Array[Int]
  val dy: Array[Int]

  def loadImage(): Unit = {
    if (name != null)
      image = new ImageView("Resources/Chess/" + name + ".png")
  }

  def wantPromote(): Boolean = {
    false
  }

  def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean

  def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]]

  protected def validateMoveImpl(board: Array[Array[Piece]], x: Int, y: Int, i: Int): Boolean

  protected def validatedMovesImpl(board: Array[Array[Piece]], x: Int, y: Int, i: Int): Boolean

  protected def loopTemplate(board: Array[Array[Piece]], newX: Int, newY: Int,
                             execute: (Array[Array[Piece]], Int, Int, Int) => Boolean): Moves = {
    if (newX > 7 || newX < 0 || newY > 7 || newY < 0)
      return moves

    for (i <- dx.indices) {
      if (execute(board, newX, newY, i))
        return moves
    }
    moves
  }

  def canEat(board: Array[Array[Piece]], atkRow: Int, atkCol: Int): Boolean = {
    if (board(atkRow)(atkCol) != null && color == board(atkRow)(atkCol).color)
      false
    else
      true
  }

  protected def clear(): Unit = {
    moves.valid = false
    moves.validMoves = Array[Pair[Int, Int]]()
  }
}
