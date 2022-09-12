package Chess

import Base.{Controller, MoveValidation, Piece, State}
import Chess.Pieces.ChessPiece
import javafx.util.Pair

class ChessController extends Controller {
  override def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        val curPiece = gameBoard(i)(j).asInstanceOf[ChessPiece]
        if (curPiece != null && curPiece.color == turn) {
          val oldRow = curPiece.curRow
          val oldCol = curPiece.curCol
          val availableMoves = curPiece.validatedMoves(gameBoard)

          for (move <- availableMoves.indices) {
            val newRow = availableMoves(move).getKey
            val newCol = availableMoves(move).getValue
            val removed = createState(gameBoard, curPiece, newRow, newCol)

            if (!checkMate(gameBoard, turn)) {
              restoreState(gameBoard, curPiece, removed, oldRow, oldCol, newRow, newCol)
              return false
            }
            restoreState(gameBoard, curPiece, removed, oldRow, oldCol, newRow, newCol)
          }
        }
      }
    }
    true
  }

  def checkMate(gameBoard: Array[Array[Piece]], turn: Int, r: Int = -1, c: Int = -1): Boolean = {
    val enemyTurn = 1 - turn
    var row, col: Int = -1

    if (r == -1) {
      val kingPiece: ChessPiece = findKing(gameBoard, turn)
      row = kingPiece.curRow
      col = kingPiece.curCol
    } else {
      row = r
      col = c
    }
    val enemyKing = findKing(gameBoard, enemyTurn)

    for (i <- gameBoard.indices)
      for (j <- gameBoard(i).indices) {
        val curPiece = gameBoard(i)(j).asInstanceOf[ChessPiece]
        if (curPiece != null && curPiece.color == enemyTurn && curPiece != enemyKing) {
          val s: State = new State(curPiece.curRow, curPiece.curCol, row, col, enemyTurn)

          if (movementValidation(gameBoard, s).valid)
            return true
        }
      }
    false
  }

  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    val piece = gameBoard(state.oldRow)(state.oldCol).asInstanceOf[ChessPiece]

    if (piece.validateMove(gameBoard, state.newRow, state.newCol))
      new MoveValidation(null, true)
    else
      new MoveValidation(null, false)
  }

  def findKing(gameBoard: Array[Array[Piece]], turn: Int): ChessPiece = {
    val kings: Array[String] = Array(ChessEn.WhiteKing, ChessEn.BlackKing)
    for (i <- gameBoard.indices)
      for (j <- gameBoard(i).indices)
        if (gameBoard(i)(j) != null && gameBoard(i)(j).name == kings(turn))
          return gameBoard(i)(j).asInstanceOf[ChessPiece]
    null
  }

  override def checkTie(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    if (!checkMate(gameBoard, turn))
      gameBoard.foreach(row => row.foreach(
        piece =>
          if (piece.color == turn)
            return piece.asInstanceOf[ChessPiece].validatedMoves(gameBoard).length > 0
        )
      )

    true
  }

  def getPlayerPieces(color: Int): Pair[Int, Int] = {
    var p: Pair[Int, Int] = null

    if (color == ChessEn.Black)
      p = new Pair[Int, Int](0, 1)
    else
      p = new Pair[Int, Int](6, 7)

    p
  }

  def kingCastling(gameBoard: Array[Array[Piece]], move: State): Int = {
    var oldRookCol, newRookCol: Int = -1
    if (move.newCol > move.oldCol) {
      newRookCol = 5
      oldRookCol = 7
    } else {
      newRookCol = 3
      oldRookCol = 0
    }

    gameBoard(move.newRow)(newRookCol) = gameBoard(move.newRow)(oldRookCol)
    gameBoard(move.newRow)(oldRookCol).curCol = newRookCol
    gameBoard(move.newRow)(oldRookCol) = null
    newRookCol
  }
}
