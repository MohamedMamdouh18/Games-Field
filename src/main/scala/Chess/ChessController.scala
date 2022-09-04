package Chess

import Base.{Controller, MoveValidation, Piece, State}
import Chess.Pieces.ChessPiece

import scala.util.control.Breaks.breakable

class ChessController extends Controller {
  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    val piece = gameBoard(state.oldRow)(state.oldCol).asInstanceOf[ChessPiece]

    if (piece.validateMove(gameBoard, state.newRow, state.newCol))
      new MoveValidation(null, true)
    else
      new MoveValidation(null, false)
  }

  override def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        val curPiece = gameBoard(i)(j).asInstanceOf[ChessPiece]
        if (curPiece != null && curPiece.color == turn) {
          val availableMoves = curPiece.validatedMoves(gameBoard)
          for (move <- availableMoves.indices) {
            val newX = availableMoves(move).getKey
            val newY = availableMoves(move).getValue
            val newBoard = gameBoard.map(_.clone())

            val modPiece = newBoard(i)(j)
            newBoard(modPiece.curRow)(modPiece.curCol) = null
            modPiece.curRow = newX
            modPiece.curCol = newY
            newBoard(modPiece.curRow)(modPiece.curCol) = modPiece

            if (!checkMate(newBoard, turn))
              return false
          }
        }
      }
    }
    true
  }

  def checkMate(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {

    val enemyTurn = 1 - turn
    val kingPiece: ChessPiece = findKing(gameBoard, turn)
    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        breakable {
          val curPiece = gameBoard(i)(j).asInstanceOf[ChessPiece]
          if (curPiece != null && curPiece.color == enemyTurn &&
            curPiece.validateMove(gameBoard, kingPiece.curRow, kingPiece.curCol)) {
            println(curPiece.name + " " + "true")
            return true
          }

          else if (curPiece != null && curPiece.color == enemyTurn) println(curPiece.name + " " + "false")
        }
      }
    }
    false
  }

  def findKing(gameBoard: Array[Array[Piece]], turn: Int): ChessPiece = {
    val kings: Array[String] = Array(ChessPieceEn.WhiteKing, ChessPieceEn.BlackKing)
    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        if (gameBoard(i)(j) != null && gameBoard(i)(j).name == kings(turn))
          return gameBoard(i)(j).asInstanceOf[ChessPiece]
      }
    }
    null
  }
}
