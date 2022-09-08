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

    val move: State = miniMax(gameBoard, observer.turn)
    var src: Node = null

    gameDrawer.gameBoard.getChildren.forEach(node => {
      if (GridPane.getColumnIndex(node) == move.oldCol && GridPane.getRowIndex(node) == move.oldRow) {
        src = node
        gameDrawer.movementDraw(src, move)
        Notify()
        return
      }
    })
  }

  def max_value(board: Array[Array[Piece]], bestScore: Int, turn: Int): Pair[Int, State] = {
    println("max")
    for (i <- board.indices) {
      for (j <- board.indices) {
        if (board(i)(j) != null) print(board(i)(j).name + " ")
        else print("| ")
      }
      println()
    }
    println()
    if (gameController.checkEndGame(board)) {
      println(turn)
      return new Pair[Int, State](estimator(turn), null)
    }

    var score = Int.MinValue
    var bestState: State = null

    for (move <- moves(board)) {
      val ans = min_value(result(board, move, turn), score, 1 - turn)
      if (score <= ans.getKey) {
        score = ans.getKey
        move.turn = turn
        bestState = move
      }
      if (score >= bestScore)
        return new Pair[Int, State](Int.MaxValue, null)
    }

    new Pair[Int, State](score, bestState)
  }

  def min_value(board: Array[Array[Piece]], bestScore: Int, turn: Int): Pair[Int, State] = {
    println("min")
    for (i <- board.indices) {
      for (j <- board.indices) {
        if (board(i)(j) != null) print(board(i)(j).name + " ")
        else print("| ")
      }
      println()
    }
    println()
    if (gameController.checkEndGame(board)) {
      println(turn)
      return new Pair[Int, State](estimator(turn), null)
    }

    var score = Int.MaxValue
    var bestState: State = null

    for (move <- moves(board)) {
      val ans = max_value(result(board, move, turn), score, 1 - turn)
      if (score >= ans.getKey) {
        score = ans.getKey
        move.turn = turn
        bestState = move
      }
      if (score <= bestScore)
        return new Pair[Int, State](Int.MinValue, null)
    }

    new Pair[Int, State](score, bestState)
  }

  private def moves(board: Array[Array[Piece]]): Array[State] = {
    var moves: Array[State] = Array()

    for (i <- 0 until 3)
      for (j <- 0 until 3)
        if (board(i)(j) == null)
          moves = moves :+ new State(i, j, 0, 0, -1)

    moves
  }

  private def estimator(t: Int): Int = {
    if (t == 0) return -1 else return 1

    0
  }

  private def result(board: Array[Array[Piece]], state: State, turn: Int): Array[Array[Piece]] = {
    val newBoard = copyBoard(board)

    newBoard(state.oldRow)(state.oldCol) = new Piece(if (turn == 0) XOEn.X else XOEn.O,
      state.oldRow, state.oldCol, turn)

    newBoard
  }

  private def miniMax(board: Array[Array[Piece]], t: Int): State = {
    if (observer.gameEnded)
      return new State(0, 0, 0, 0, 0)

    var ans: Pair[Int, State] = null
    if (t == 0)
      ans = max_value(board, Int.MaxValue, t)
    else
      ans = min_value(board, Int.MinValue, t)

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
