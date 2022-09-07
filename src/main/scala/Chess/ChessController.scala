package Chess

import Base.{Controller, MoveValidation, Piece, State}
import Chess.Pieces.ChessPiece

import scala.util.control.Breaks.breakable

class ChessController extends Controller {
  override def checkEndGame(gameBoard: Array[Array[Piece]], turn: Int): Boolean = {
    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        val curPiece = gameBoard(i)(j).asInstanceOf[ChessPiece]
        if (curPiece != null && curPiece.color == turn) {
          val availableMoves = curPiece.validatedMoves(gameBoard)
          for (move <- availableMoves.indices) {
            val newX = availableMoves(move).getKey
            val newY = availableMoves(move).getValue
            val newBoard = copyBoard(gameBoard)

            createState(newBoard, newBoard(i)(j), newX, newY)

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
          if (curPiece != null && curPiece.color == enemyTurn) {
            val s: State = new State(curPiece.curRow, curPiece.curCol, kingPiece.curRow, kingPiece.curCol, enemyTurn)

            if (movementValidation(gameBoard, s).valid)
              return true
          }
        }
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
    val kings: Array[String] = Array(ChessPieceEn.WhiteKing, ChessPieceEn.BlackKing)
    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        if (gameBoard(i)(j) != null && gameBoard(i)(j).name == kings(turn))
          return gameBoard(i)(j).asInstanceOf[ChessPiece]
      }
    }
    null
  }

  def copyBoard(gameBoard: Array[Array[Piece]]): Array[Array[Piece]] = {
    val newBoard: Array[Array[Piece]] = Array.tabulate(8, 8)((_, _) => null)
    for (i <- newBoard.indices) {
      for (j <- newBoard(i).indices) {
        if (gameBoard(i)(j) != null) {
          newBoard(i)(j) = gameBoard(i)(j).clone()
        }
      }
    }
    newBoard
  }

  def createState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, newX: Int, newY: Int): Piece = {
    val removed = gameBoard(newX)(newY)

    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = null
    modifiedPiece.curRow = newX
    modifiedPiece.curCol = newY
    gameBoard(modifiedPiece.curRow)(modifiedPiece.curCol) = modifiedPiece

    removed
  }

  def restoreState(gameBoard: Array[Array[Piece]], modifiedPiece: Piece, removedPiece : Piece ,oldRow : Int , oldCol :Int ,newRow: Int, newCol: Int): Unit ={
    gameBoard(oldRow)(oldCol) = modifiedPiece
    gameBoard(newRow)(newCol) = removedPiece

    modifiedPiece.curRow = oldRow
    modifiedPiece.curCol = oldCol
  }
}
