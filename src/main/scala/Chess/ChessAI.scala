package Chess

import Base._
import Chess.Pieces.{ChessPiece, King, Pawn, Queen}
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class ChessAI extends Player {
  private val estimator: ChessEstimator = new ChessEstimator
  override var observer: GameEngine = _
  private var gameBoard: Array[Array[Piece]] = _
  private var move: State = _

  override def run(buts: GridPane = null): Unit = {
    gameBoard = observer.gameBoard

    val p: Pair[Int, Int] = ChessController.getPlayerPieces(color)
    ChessDrawer.setEvents((_: Node) => {}, gameBoard, p.getKey, p.getValue)
  }

  override def Notify(): Unit = {
    observer.asInstanceOf[ChessEngine].ReleaseLogic(null, move)
    observer.update()
  }

  override def Movement(source: Node): Unit = {
    move = miniMax(gameBoard, observer.turn, Int.MinValue, Int.MaxValue, depth).getKey
    Notify()
  }

  private def miniMax(board: Array[Array[Piece]], turn: Int, a: Int, b: Int, depth: Int): Pair[State, Int] = {
    if (ChessController.checkEndGame(board, turn)) {
      if (ChessController.checkMate(board, turn))
        return new Pair[State, Int](null, if (1 - turn == ChessEn.White) 20000 else -20000)
      else
        return new Pair[State, Int](null, 0)
    } else if (ChessController.checkEndGame(board, 1 - turn)) {
      if (ChessController.checkMate(board, 1 - turn))
        return new Pair[State, Int](null, if (turn == ChessEn.White) 20000 else -20000)
      else
        return new Pair[State, Int](null, 0)
    }

    if (depth == 0)
      return new Pair[State, Int](null, estimator.estimate(gameBoard))

    var score = if (turn == ChessEn.White) Int.MinValue else Int.MaxValue
    var bestMove: State = null
    var alpha: Int = a
    var beta: Int = b

    board.foreach(_.foreach(piece => {
      if (piece != null && piece.color == turn) {
        val curPiece = piece.asInstanceOf[ChessPiece]
        val oldRow = curPiece.curRow
        val oldCol = curPiece.curCol

        val availableMoves = curPiece.validatedMoves(board)
        for (move <- availableMoves.indices) {
          val newRow = availableMoves(move).getKey
          val newCol = availableMoves(move).getValue
          val state: State = new State(oldRow, oldCol, newRow, newCol, turn)

          val removed = ChessController.createState(board, curPiece, newRow, newCol)

          val fm = curPiece.firstMove
          var castle, promote: Boolean = false
          curPiece.firstMove = false

          if (curPiece.isInstanceOf[King] && Math.abs(curPiece.curCol - newCol) == 2) {
            castle = true
            ChessController.kingCastling(board, state)
          }

          if (curPiece.wantPromote()) {
            promote = false
            board(newRow)(newCol) =
              new Queen(if (turn == 1) ChessEn.BlackQueen else ChessEn.WhiteQueen, newRow, newCol, turn)
          }

          var currentScore: Int = if (turn == ChessEn.White) Int.MinValue else Int.MaxValue
          if (!ChessController.checkMate(board, turn))
            currentScore = miniMax(board, 1 - turn, alpha, beta, depth - 1).getValue

          ChessController.restoreState(board, curPiece, removed, state)

          if (castle)
            reverseKingCastling(board, state)
          if (promote)
            reversePromotion(board, state)

          castle = false
          promote = false
          curPiece.firstMove = fm

          if (turn == ChessEn.Black) {
            if (currentScore < score) {
              score = currentScore
              bestMove = new State(piece.curRow, piece.curCol, newRow, newCol, turn)
            }
            beta = Math.min(beta, score)
          } else {
            if (currentScore > score) {
              score = currentScore
              bestMove = new State(piece.curRow, piece.curCol, newRow, newCol, turn)
            }
            alpha = Math.max(alpha, score)
          }
          if (alpha >= beta)
            return new Pair[State, Int](bestMove, score)
        }
      }
    }))

    new Pair[State, Int](bestMove, score)
  }

  private def reverseKingCastling(board: Array[Array[Piece]], state: State): Unit = {
    val p = ChessController.getRookPlace(move)
    val oldRookCol = p.getKey
    val newRookCol = p.getValue

    board(state.newRow)(oldRookCol) = board(state.newRow)(newRookCol)
    board(state.newRow)(newRookCol).curCol = oldRookCol
    board(state.newRow)(newRookCol) = null
  }

  private def reversePromotion(board: Array[Array[Piece]], state: State): Unit = {
    val pawn = new Pawn(if (state.turn == ChessEn.Black) ChessEn.BlackPawn else ChessEn.WhitePawn,
      state.oldRow, state.oldCol, state.turn)

    board(state.oldRow)(state.oldCol) = pawn
    board(state.newRow)(state.newCol) = null
  }
}
