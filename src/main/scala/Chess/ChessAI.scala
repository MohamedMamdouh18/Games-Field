package Chess

import Base.Player.Player
import Base._
import Chess.Pieces.ChessPiece
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class ChessAI extends Player {
  override var observer: GameEngine = _
  private var gameController: ChessController = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _
  private val estimator: ChessEstimator = new ChessEstimator

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard

    val p: Pair[Int, Int] = gameController.getPlayerPieces(color)
    gameDrawer.setEvents((_: Node) => {}, gameBoard, p.getKey, p.getValue)
  }

  override def Movement(source: Node): Unit = {
    val move: State = miniMax(gameBoard, observer.turn, Int.MinValue, Int.MaxValue, 5).getKey
    val curPiece: ChessPiece = gameBoard(move.oldRow)(move.oldCol).asInstanceOf[ChessPiece]

    if (gameBoard(move.newRow)(move.newCol) != null) {
      observer.score(1 - color) -= gameBoard(move.newRow)(move.newCol).asInstanceOf[ChessPiece].rank
      gameDrawer.gameBoard.getChildren.remove(gameBoard(move.newRow)(move.newCol).image)
    }

    if (curPiece.castled) {
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
      gameDrawer.movementDraw(gameBoard(move.newRow)(newRookCol).image,
        new State(0, 0, move.newRow, newRookCol, -1), gameBoard(move.newRow)(newRookCol).image)
      curPiece.castled = false
    }

    gameBoard(move.oldRow)(move.oldCol) = null
    gameBoard(move.newRow)(move.newCol) = curPiece
    gameDrawer.movementDraw(curPiece.image, new State(0, 0, move.newRow, move.newCol, 0), curPiece.image)

    curPiece.firstMove = false
    curPiece.curCol = move.newCol
    curPiece.curRow = move.newRow
    Notify()
  }

  private def miniMax(board: Array[Array[Piece]], turn: Int, a: Int, b: Int, depth: Int): Pair[State, Int] = {
    if (gameController.checkEndGame(board, turn))
      return new Pair[State, Int](null, if (turn == 0) 20000 else -20000)
    else if (gameController.checkEndGame(board, 1 - turn))
      return new Pair[State, Int](null, if (1 - turn == 0) 20000 else -20000)
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

          val removed = gameController.createState(board, curPiece, newRow, newCol)
          val fm = curPiece.firstMove
          val c = curPiece.castled
          curPiece.castled = false
          curPiece.firstMove = false

          var currentScore: Int = if (turn == 0) Int.MinValue else Int.MaxValue
          if (!gameController.checkMate(board, turn))
            currentScore = miniMax(board, 1 - turn, alpha, beta, depth - 1).getValue

          gameController.restoreState(board, curPiece, removed, oldRow, oldCol, newRow, newCol)
          curPiece.firstMove = fm
          curPiece.castled = c

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
