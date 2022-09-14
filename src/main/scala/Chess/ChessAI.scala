package Chess

import Base.Player.Player
import Base._
import Chess.Pieces.{ChessPiece, King, Queen}
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class ChessAI extends Player {
  private val estimator: ChessEstimator = new ChessEstimator
  override var observer: GameEngine = _
  private var gameController: ChessController = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard

    val p: Pair[Int, Int] = gameController.getPlayerPieces(color)
    gameDrawer.setEvents((_: Node) => {}, gameBoard, p.getKey, p.getValue)
  }

  override def Movement(source: Node): Unit = {
    val move: State = miniMax(gameBoard, observer.turn, Int.MinValue, Int.MaxValue, 5).getKey
    var curPiece: ChessPiece = gameBoard(move.oldRow)(move.oldCol).asInstanceOf[ChessPiece]
    gameDrawer.asInstanceOf[ChessDrawer].highlightSquares(move)

    if (gameBoard(move.newRow)(move.newCol) != null) {
      observer.score(1 - color) -= gameBoard(move.newRow)(move.newCol).asInstanceOf[ChessPiece].rank
      gameDrawer.gameBoard.getChildren.remove(gameBoard(move.newRow)(move.newCol).image)
    }

    if (curPiece.wantCastle(move.oldCol, move.newCol)) {
      val newRookCol = gameController.kingCastling(gameBoard,
        new State(move.oldRow, move.oldCol, move.newRow, move.newCol, color))

      gameDrawer.movementDraw(gameBoard(move.newRow)(newRookCol).image,
        new State(0, 0, move.newRow, newRookCol, -1), gameBoard(move.newRow)(newRookCol).image)
    }

    gameBoard(move.oldRow)(move.oldCol) = null
    gameBoard(move.newRow)(move.newCol) = curPiece
    curPiece.firstMove = false
    curPiece.curCol = move.newCol
    curPiece.curRow = move.newRow

    if (curPiece.wantPromote()) {
      gameBoard(move.newRow)(move.newCol) = new Queen(if (color == 1) ChessEn.BlackQueen else ChessEn.WhiteQueen, move.newRow, move.newCol, color)
      gameDrawer.gameBoard.getChildren.remove(curPiece.image)
      curPiece = gameBoard(move.newRow)(move.newCol).asInstanceOf[ChessPiece]
    }

    gameDrawer.movementDraw(curPiece.image, new State(0, 0, move.newRow, move.newCol, -1), curPiece.image)

    Notify()
  }

  private def miniMax(board: Array[Array[Piece]], turn: Int, a: Int, b: Int, depth: Int): Pair[State, Int] = {
    if (gameController.checkEndGame(board, turn)) {
      if (gameController.checkMate(board, turn))
        return new Pair[State, Int](null, if (1 - turn == ChessEn.White) 20000 else -20000)
      else
        return new Pair[State, Int](null, 0)
    } else if (gameController.checkEndGame(board, 1 - turn)) {
      if (gameController.checkMate(board, 1 - turn))
        return new Pair[State, Int](null, if (turn == ChessEn.White) 20000 else -20000)
      else
        return new Pair[State, Int](null, 0)
    }

    if (depth == 0)
      return new Pair[State, Int](null, if (turn == 0) estimator.estimate(gameBoard)
      else estimator.estimate(gameBoard) * -1)

    var score = if (turn == 0) Int.MinValue else Int.MaxValue
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

          if (board(newRow)(newCol).isInstanceOf[King]) {
            println("in")
          }

          val removed = gameController.createState(board, curPiece, newRow, newCol)
          val fm = curPiece.firstMove
          curPiece.firstMove = false

          if (curPiece.isInstanceOf[King] && Math.abs(curPiece.curCol - newCol) == 2)
            gameController.kingCastling(board, new State(oldRow, oldCol, newRow, newCol, turn))

          if (curPiece.wantPromote())
            board(newRow)(newCol) = new Queen(if (turn == 1) ChessEn.BlackQueen else ChessEn.WhiteQueen, newRow, newCol, turn)

          var currentScore: Int = if (turn == 0) Int.MinValue else Int.MaxValue
          if (!gameController.checkMate(board, turn))
            currentScore = miniMax(board, 1 - turn, alpha, beta, depth - 1).getValue

          gameController.restoreState(board, curPiece, removed, oldRow, oldCol, newRow, newCol)
          curPiece.firstMove = fm

          //maximize white && minimize black
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
}
