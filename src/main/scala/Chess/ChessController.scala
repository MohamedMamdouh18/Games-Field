package Chess

import Base.{Controller, MoveValidation, Piece, State}
import Chess.Pieces.ChessPiece

import scala.util.control.Breaks.{break, breakable}

class ChessController extends Controller {
  override def movementValidation(gameBoard: Array[Array[Piece]], state: State): MoveValidation = {
    val piece = gameBoard(state.oldRow)(state.oldCol).asInstanceOf[ChessPiece]

    if (piece.validateMove(gameBoard, state.newRow, state.newCol))
      new MoveValidation(null, true)
    else
      new MoveValidation(null, false)
  }

  override def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    val enemyTurn = 1 - turn

    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        breakable {
          val curPiece = gameBoard(i)(j).asInstanceOf[ChessPiece]
          if (curPiece == null || curPiece.color != turn) break

          var availableMoves = curPiece.validatedMoves(gameBoard)
          for(move <- availableMoves.indices){
           val newX = availableMoves(move).getKey
           val newY = availableMoves(move).getValue
           val newBoard = gameBoard.map(_.clone())

            val modPiece = newBoard(i)(j)
            modPiece.curRow = newX
            curPiece.curCol = newY
            newBoard(curPiece.curRow)(curPiece.curCol) = null
            newBoard(newX)(newY) = modPiece

            if(!checkMate(newBoard , turn))
              return false
          }
        }
      }
    }
    true
  }

  def checkMate(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    val enemyTurn = 1 - turn
    val kingPiece: ChessPiece = findKing(gameBoard , turn)

    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        breakable {
          val curPiece = gameBoard(i)(j).asInstanceOf[ChessPiece]
          if (curPiece == null || curPiece.color != enemyTurn) break
          if (curPiece.validateMove(gameBoard, kingPiece.curRow, kingPiece.curCol))
            return true
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
