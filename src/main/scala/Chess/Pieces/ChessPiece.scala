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

  /**
   * Returns true if the piece can promote.
   *
   * @return true if the piece can promote.
   */
  def wantPromote(): Boolean = {
    false
  }

  /**
   * Returns true if the piece can castle.
   *
   * @param oldCol the king's old column to be castled.
   * @param newCol the king's new column to be castled.
   * @return true if the piece can castle.
   */
  def wantCastle(oldCol: Int, newCol: Int): Boolean = {
    false
  }

  /**
   * Returns true if the new move is valid.
   *
   * @param board the game board which has been played so far.
   * @param newX  the new row the piece will go to it.
   * @param newY  the new column the piece will go to it.
   * @return true if the new move is valid.
   */
  def validateMove(board: Array[Array[Piece]], newX: Int, newY: Int): Boolean

  /**
   * Returns all the valid movements for this piece.
   *
   * @param board the game board which has been played so far.
   * @return all the valid movements for this piece.
   */
  def validatedMoves(board: Array[Array[Piece]]): Array[Pair[Int, Int]]

  /**
   * Loads the image of the piece to the GUI board.
   */
  def loadImage(): Unit = {
    if (name != null)
      image = new ImageView("Resources/Chess/" + name + ".png")
  }

  /**
   * Returns true if the new move is valid.
   *
   * @param s holds the piece's current state and next state.
   * @return true if the new move is valid.
   */
  protected def validateMoveImpl(s: State): Boolean = {
    if (s.newCol == s.oldCol && s.newRow == s.oldRow)
      moves.valid = true
    moves.valid
  }

  /**
   * Returns true if the new move is valid and add this move to the array of valid moves.
   *
   * @param s holds the piece's current state and next state.
   * @return true if the new move is valid.
   */
  protected def validatedMovesImpl(s: State): Boolean = {
    moves.validMoves = moves.validMoves :+ new Pair[Int, Int](s.newRow, s.newCol)
    moves.valid
  }

  /**
   * Returns a moves object and it depends on the execute function so it will return object holds all the valid moves or if the new move was valid or not.
   *
   * @param board the game board which has been played so far.
   * @param newX  the new row the piece will go to it.
   * @param newY  the new column the piece will go to it.
   * @param execute a function that returns true if the game engine checks if the new move is valid, returns false otherwise. Also it returns false if the game needs all valid moves so it continues to loop on all valid moves.
   * @param e end index for the piece.
   * @return Returns a moves object.
   */
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

  /**
   * Returns true if the current piece can eat the attacked piece.
   *
   * @param board  the game board which has been played so far.
   * @param atkRow the row of attacked cell.
   * @param atkCol the column of attacked cell.
   * @return true if the current piece can eat the attacked piece.
   */
  def canEat(board: Array[Array[Piece]], atkRow: Int, atkCol: Int): Boolean = {
    if ((board(atkRow)(atkCol) != null && color == board(atkRow)(atkCol).color) || board(atkRow)(atkCol) == null)
      false
    else
      true
  }

  /**
   * Clears the moves array.
   */
  protected def clear(): Unit = {
    moves.valid = false
    moves.validMoves = Array[Pair[Int, Int]]()
  }
}
