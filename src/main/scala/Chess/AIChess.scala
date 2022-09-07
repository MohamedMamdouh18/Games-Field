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
    if (color == 1) {
      s = 0
      e = 1
    } else {
      s = 6
      e = 7
    }

    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard

    gameDrawer.setEvents((node: Node) => {}, gameBoard, s, e)
  }

  override def Movement(source: Node): Unit = {
    val move: State = miniMax(gameBoard, observer.turn, if (color == 1) 1300 else -1300, 2).getKey

    val oldRow: Int = move.oldRow
    val oldCol: Int = move.oldCol
    val newRow: Int = move.newRow
    val newCol: Int = move.newCol
    val curPiece: ChessPiece = gameBoard(oldRow)(oldCol).asInstanceOf[ChessPiece]

    if (gameBoard(newRow)(newCol) != null) {
      observer.score(1 - color) -= gameBoard(newRow)(newCol).asInstanceOf[ChessPiece].rank
      gameDrawer.gameBoard.getChildren.remove(gameBoard(newRow)(newCol).image)
    }

    gameBoard(oldRow)(oldCol) = null
    gameBoard(newRow)(newCol) = curPiece
    gameDrawer.movementDraw(curPiece.image, new State(0, 0, newRow, newCol, 0), curPiece.image)

    curPiece.firstMove = false
    curPiece.curCol = newCol
    curPiece.curRow = newRow
    Notify()
  }

  private def miniMax(board: Array[Array[Piece]], t: Int, bestScore: Int, depth: Int): Pair[State, Int] = {
    if (depth == 0) return new Pair[State, Int](null, estimator(board))

    var score: Int = 0
    if (t == 1)
      score = 1300
    else
      score = -1300

    var bestMove: State = null

    board.foreach(_.foreach(piece => {
      if (piece != null && piece.color == t) {
        val curPiece = piece.asInstanceOf[ChessPiece]
        val availableMoves = curPiece.validatedMoves(gameBoard)
        for (move <- availableMoves.indices) {
          val newRow = availableMoves(move).getKey
          val newCol = availableMoves(move).getValue
          val newBoard = gameController.copyBoard(board)

          gameController.createState(newBoard, newBoard(piece.curRow)(piece.curCol), newRow, newCol)
          val currentScore = miniMax(newBoard, 1 - t, score, depth - 1)

          //maximize white && minimize black
          if (t == 1) {
            if (currentScore.getValue < score) {
              score = currentScore.getValue
              bestMove = new State(piece.curRow, piece.curCol, newRow, newCol, t)
            }
            if(currentScore.getValue > bestScore){
              return new Pair[State, Int](bestMove, score)
            }
          } else {
            if (currentScore.getValue > score) {
              score = currentScore.getValue
              bestMove = new State(piece.curRow, piece.curCol, newRow, newCol, t)
            }
            if(currentScore.getValue < bestScore){
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
