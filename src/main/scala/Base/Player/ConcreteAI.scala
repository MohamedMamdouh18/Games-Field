package Base.Player

import Base._
import XO.XOEn
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class ConcreteAI extends Player {
  override var observer: GameEngine = _
  private var gameController: Controller = _
  private var gameDrawer: Drawer = _
  private var gameBoard: Array[Array[Piece]] = _

  override def run(buts: GridPane = null): Unit = {
    gameDrawer = observer.gameDrawer
    gameController = observer.gameController
    gameBoard = observer.gameBoard

    gameDrawer.setEvents((_: Node) => {}, gameBoard)
  }

  override def Movement(source: Node): Unit = {
    if (observer.gameEnded)
      return

    val move: State = miniMax(gameBoard)
    var src: Node = null

    gameDrawer.gameBoard.getChildren.forEach(node => {
      if (GridPane.getColumnIndex(node) == move.oldCol && GridPane.getRowIndex(node) == move.oldRow) {
        src = node
        gameBoard(move.oldRow)(move.oldCol) = new Piece(if (observer.turn == 0) XOEn.X else XOEn.O,
          move.oldRow, move.oldCol, observer.turn)
        move.turn = observer.turn
        gameDrawer.movementDraw(src, move)
        Notify()
        return
      }
    })
  }

  def max_value(board: Array[Array[Piece]], bestScore: Int): Pair[Int, State] = {
    if (terminal(board))
      return new Pair[Int, State](estimator(board), null)

    var score = Int.MinValue
    var bestState: State = null

    for (move <- moves(board)) {
      val ans = min_value(result(board, move), score)
      if (score <= ans.getKey) {
        score = ans.getKey
        bestState = move
      }
      if (score >= bestScore)
        return new Pair[Int, State](Int.MaxValue, null)
    }

    new Pair[Int, State](score, bestState)
  }

  def min_value(board: Array[Array[Piece]], bestScore: Int): Pair[Int, State] = {
    if (terminal(board))
      return new Pair[Int, State](estimator(board), null)

    var score = Int.MaxValue
    var bestState: State = null

    for (move <- moves(board)) {
      val ans = max_value(result(board, move), score)
      if (score >= ans.getKey) {
        score = ans.getKey
        bestState = move
      }
      if (score <= bestScore)
        return new Pair[Int, State](Int.MinValue, null)
    }

    new Pair[Int, State](score, bestState)
  }

  private def player(board: Array[Array[Piece]]): String = {
    var cnt = 0

    for (i <- 0 until 3)
      for (j <- 0 until 3)
        if (board(i)(j) != null)
          cnt = cnt + 1

    if ((cnt & 1) == 0)
      XOEn.X
    else
      XOEn.O
  }

  private def moves(board: Array[Array[Piece]]): Set[State] = {
    var moves: Set[State] = Set()

    for (i <- 0 until 3)
      for (j <- 0 until 3)
        if (board(i)(j) == null)
          moves = moves ++ Set(new State(i, j, 0, 0, -1))

    moves
  }

  private def estimator(board: Array[Array[Piece]]): Int = {
    val winnerName = winner(board)

    if (winnerName == XOEn.X)
      1
    else if (winnerName == XOEn.O)
      -1
    else
      0
  }

  private def result(board: Array[Array[Piece]], state: State): Array[Array[Piece]] = {
    val newBoard = copyBoard(board)
    val t = player(board)

    newBoard(state.oldRow)(state.oldCol) = new Piece(t, state.oldRow, state.oldCol, if (t == XOEn.X) 0 else 1)

    newBoard
  }

  private def terminal(board: Array[Array[Piece]]): Boolean = {
    if (winner(board) != null)
      return true

    for (i <- 0 until 3)
      for (j <- 0 until 3)
        if (board(i)(j) == null)
          return false

    true
  }

  private def determineWinner(piece: Piece): String = {
    if (piece.name == XOEn.X)
      XOEn.X
    else if (piece.name == XOEn.O)
      XOEn.O
    else
      null
  }

  private def winner(board: Array[Array[Piece]]): String = {
    var winnerName: String = null
    for (i <- 0 until 3) {
      if (board(i)(0) != null && board(i)(1) != null && board(i)(2) != null &&
        board(i)(0).name == board(i)(1).name && board(i)(0).name == board(i)(2).name) {
        winnerName = determineWinner(board(i)(0))
        if (winnerName != null)
          return winnerName
      }
    }

    for (i <- 0 until 3) {
      if (board(0)(i) != null && board(1)(i) != null && board(2)(i) != null &&
        board(0)(i).name == board(1)(i).name && board(0)(i).name == board(2)(i).name) {
        winnerName = determineWinner(board(0)(i))
        if (winnerName != null)
          return winnerName
      }
    }

    if (board(0)(0) != null && board(1)(1) != null && board(2)(2) != null &&
      board(0)(0).name == board(1)(1).name && board(0)(0).name == board(2)(2).name) {
      winnerName = determineWinner(board(1)(1))
      if (winnerName != null)
        return winnerName
    }

    if (board(0)(2) != null && board(1)(1) != null && board(2)(0) != null &&
      board(0)(2).name == board(1)(1).name && board(0)(2).name == board(2)(0).name) {
      winnerName = determineWinner(board(1)(1))
      if (winnerName != null)
        return winnerName
    }

    null
  }

  private def miniMax(board: Array[Array[Piece]]): State = {
    if (terminal(board))
      return new State(-1, -1, -1, -1, -1)

    var ans: Pair[Int, State] = null

    if (player(board) == XOEn.X)
      ans = max_value(board, Int.MaxValue)
    else
      ans = min_value(board, Int.MinValue)

    ans.getValue
  }

  private def copyBoard(gameBoard: Array[Array[Piece]]): Array[Array[Piece]] = {
    val newBoard: Array[Array[Piece]] = Array.tabulate(3, 3)((_, _) => null)
    for (i <- newBoard.indices) {
      for (j <- newBoard(i).indices) {
        if (gameBoard(i)(j) != null) {
          newBoard(i)(j) = gameBoard(i)(j).clone()
        }
      }
    }
    newBoard
  }
}
