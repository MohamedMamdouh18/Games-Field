package Chess.Pieces

import Base.{Piece, State}
import Chess.ChessPieceEn
import javafx.scene.image.ImageView
import javafx.util.Pair

import scala.util.control.Breaks.{break, breakable}

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

  protected def validateMoveImpl(s: State): Boolean = {
    if (s.newCol == s.oldCol && s.newRow == s.oldRow)
      moves.valid = true
    moves.valid
  }

  protected def validatedMovesImpl(s: State): Boolean = {
    moves.validMoves = moves.validMoves :+ new Pair[Int, Int](s.newRow, s.newCol)
    moves.valid
  }

  protected def loopTemplate(board: Array[Array[Piece]], newX: Int, newY: Int,
                             execute: State => Boolean, e: Int): Moves = {
    if (newX > 7 || newX < 0 || newY > 7 || newY < 0)
      return moves

    for (i <- dx.indices) {
      breakable {
        for (j <- 1 to e) {
          val validNewX = curRow + dx(i) * j
          val validNewY = curCol + dy(i) * j

          if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7 &&
            canEat(board, validNewX, validNewY)) {

            if (execute(new State(newX, newY, validNewX, validNewY, 0)))
              return moves
          } else if (pieceName != ChessPieceEn.WhiteKnight && pieceName != ChessPieceEn.BlackKnight) {
            break
          }
        }
      }
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
