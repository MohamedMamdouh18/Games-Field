package Chess.Pieces

import Base.{Piece, State}
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

  def validatedMoves(board: Array[Array[Piece]], newX: Int, newY: Int): Array[Pair[Int, Int]]

  protected def validateMoveImpl(board: Array[Array[Piece]], s: State): Unit

  protected def validatedMovesImpl(board: Array[Array[Piece]], s: State): Unit

  protected def loopTemplate(board: Array[Array[Piece]], newX: Int, newY: Int,
                             execute: (Array[Array[Piece]], State) => Unit): Moves = {
    if (newX > 7 || newX < 0 || newY > 7 || newY < 0)
      return moves
    var validNewX, validNewY: Int = 0

    for (i <- 0 to 7) {
      validNewX = curRow + dx(i)
      validNewY = curCol + dy(i)

      if (validNewX <= 7 && validNewX >= 0 && validNewY >= 0 && validNewY <= 7 &&
        canEat(board, validNewX, validNewY)) {
        val s: State = new State(validNewX, validNewY, newX, newY, 0)

        execute(board, s)
        if (moves.valid)
          return moves
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
