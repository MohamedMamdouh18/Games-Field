package Chess.Pieces

import Base.{Piece, State}
import Chess.ChessEn
import javafx.scene.image.ImageView
import javafx.util.Pair

import scala.util.control.Breaks.{break, breakable}

abstract class ChessPiece(pieceName: String, row: Int, col: Int, color: Int) extends Piece(pieceName, row, col, color) with Cloneable {
  val dx: Array[Int]
  val dy: Array[Int]
  var firstMove: Boolean = true
  var checked: Boolean = false
  var moves: Moves = new Moves
  var rank: Int
  var evaluationMatrix: Array[Array[Double]]

  protected def loadImage(): Unit = {
    if (name != null)
      image = new ImageView("Resources/Chess/" + name + ".png")
  }

  def wantPromote(): Boolean = {
    false
  }

  def wantCastle(oldCol: Int, newCol: Int): Boolean = {
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

          if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7) {
            if (canEat(board, validNewX, validNewY) || board(validNewX)(validNewY) == null)
              if (execute(new State(newX, newY, validNewX, validNewY, -1)))
                return moves

            if (pieceName != ChessEn.WhiteKnight && pieceName != ChessEn.BlackKnight &&
              board(validNewX)(validNewY) != null)
              break
          }
        }
      }
    }
    moves
  }

  def canEat(board: Array[Array[Piece]], atkRow: Int, atkCol: Int): Boolean = {
    if ((board(atkRow)(atkCol) != null && color == board(atkRow)(atkCol).color) || board(atkRow)(atkCol) == null)
      false
    else
      true
  }

  protected def clear(): Unit = {
    moves.valid = false
    moves.validMoves = Array[Pair[Int, Int]]()
  }
}
