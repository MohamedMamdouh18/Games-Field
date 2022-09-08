package Chess

import Base.Player.Player
import Base._
import Chess.Pieces.ChessPiece
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class AIChess extends Player {
  override var observer: GameEngine = _
  private var gameController: ChessController = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _

  override def run(buts: GridPane = null): Unit = {
    var s, e: Int = 0
    if (color == ChessEn.Black) {
      s = 0
      e = 1
    } else {
      s = 6
      e = 7
    }

    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard

    gameDrawer.setEvents((_: Node) => {}, gameBoard, s, e)
  }

  override def Movement(source: Node): Unit = {
    val move: State = miniMax(gameBoard, observer.turn, if (color == 1) 1300 else -1300, 5).getKey
    val curPiece: ChessPiece = gameBoard(move.oldRow)(move.oldCol).asInstanceOf[ChessPiece]

    if (gameBoard(move.newRow)(move.newCol) != null) {
      observer.score(1 - color) -= gameBoard(move.newRow)(move.newCol).asInstanceOf[ChessPiece].rank
      gameDrawer.gameBoard.getChildren.remove(gameBoard(move.newRow)(move.newCol).image)
    }

    gameBoard(move.oldRow)(move.oldCol) = null
    gameBoard(move.newRow)(move.newCol) = curPiece
    gameDrawer.movementDraw(curPiece.image, new State(0, 0, move.newRow, move.newCol, 0), curPiece.image)

    curPiece.firstMove = false
    curPiece.curCol = move.newCol
    curPiece.curRow = move.newRow
    Notify()
  }

  private def miniMax(board: Array[Array[Piece]], t: Int, bestScore: Int, depth: Int): Pair[State, Int] = {
    if (depth == 0) return new Pair[State, Int](null, estimator(board))

    var score: Int = 0
    if (t == ChessEn.Black)
      score = 1300
    else
      score = -1300

    var bestMove: State = null

    board.foreach(_.foreach(piece => {
      if (piece != null && piece.color == t) {
        val curPiece = piece.asInstanceOf[ChessPiece]
        val oldRow = curPiece.curRow
        val oldCol = curPiece.curCol

        val availableMoves = curPiece.validatedMoves(board)
        for (move <- availableMoves.indices) {
          val newRow = availableMoves(move).getKey
          val newCol = availableMoves(move).getValue

          val removed = gameController.createState(board, curPiece, newRow, newCol)
          val currentScore = miniMax(board, 1 - t, score, depth - 1)

          gameController.restoreState(board, curPiece, removed, oldRow, oldCol, newRow, newCol)

          //maximize white && minimize black
          if (t == ChessEn.Black) {
            if (currentScore.getValue < score) {
              score = currentScore.getValue
              bestMove = new State(piece.curRow, piece.curCol, newRow, newCol, t)
            }
            if (currentScore.getValue > bestScore) {
              return new Pair[State, Int](bestMove, score)
            }
          } else {
            if (currentScore.getValue > score) {
              score = currentScore.getValue
              bestMove = new State(piece.curRow, piece.curCol, newRow, newCol, t)
            }
            if (currentScore.getValue < bestScore) {
              return new Pair[State, Int](bestMove, score)
            }
          }
        }
      }
    }))

    new Pair[State, Int](bestMove, score)
  }

  private def estimator(board: Array[Array[Piece]]): Int = {
    val score: Array[Int] = Array(0, 0)
    board.foreach(_.foreach(piece => {
      if (piece != null)
        score(piece.color) += piece.asInstanceOf[ChessPiece].rank
    }))
    score(0) - score(1)
  }
}
