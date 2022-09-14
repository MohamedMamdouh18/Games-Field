package Chess

import Base.{Drawer, Piece, State}
import Chess.Pieces._
import javafx.collections.transformation.FilteredList
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.util.Pair

import java.util.function.Predicate

class ChessDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var gameBoard: GridPane = new GridPane()
  var oldCol, oldRow: Int = 0
  var x, y: Double = 0
  var attackNodes: FilteredList[Node] = _
  var normalNodes: FilteredList[Node] = _
  var promotionMap: Array[Map[Pair[Int, Int], Promotion]] = Array(
    Map(
      new Pair[Int, Int](0, 0) -> ((r, c) => new Queen(ChessEn.WhiteQueen, r, c, 0)),
      new Pair[Int, Int](0, 1) -> ((r, c) => new Knight(ChessEn.WhiteKnight, r, c, 0)),
      new Pair[Int, Int](1, 1) -> ((r, c) => new Bishop(ChessEn.WhiteBishop, r, c, 0)),
      new Pair[Int, Int](1, 0) -> ((r, c) => new Rook(ChessEn.WhiteRook, r, c, 0))
    ),
    Map(
      new Pair[Int, Int](1, 0) -> ((r, c) => new Queen(ChessEn.BlackQueen, r, c, 1)),
      new Pair[Int, Int](1, 1) -> ((r, c) => new Knight(ChessEn.BlackKnight, r, c, 1)),
      new Pair[Int, Int](0, 1) -> ((r, c) => new Bishop(ChessEn.BlackBishop, r, c, 1)),
      new Pair[Int, Int](0, 0) -> ((r, c) => new Rook(ChessEn.BlackRook, r, c, 1))
    ),
  )

  override def drawPiece(): Unit = {
    gameBoard = drawBoard(8, 8,
      Color.rgb(236, 205, 153), Color.rgb(152, 68, 32), showGridLines = false, showMovements = true)
  }

  override def setEvents(Event: Node => Unit, board: Array[Array[Piece]], s: Int, e: Int): Unit = {
    gameBoard.setAlignment(Pos.CENTER)
    for (i <- s to e) {
      for (j <- 0 to 7) {
        if (board(i)(j) != null) {
          gameBoard.add(board(i)(j).image, j, i)
          Event(board(i)(j).image)
        }
      }
    }
  }

  def showAvailableMovements(player: ChessPlayer): Unit = {
    var moves: Array[Pair[Int, Int]] = Array()

    for (move <- player.curPiece.validatedMoves(player.gameBoard)) {
      val removed = player.gameController.createState(player.gameBoard, player.curPiece,
        move.getKey, move.getValue)

      if (!player.gameController.checkMate(player.gameBoard, player.color))
        moves = moves :+ move

      player.gameController.restoreState(player.gameBoard, player.curPiece, removed,
        player.oldRow, player.oldCol, move.getKey, move.getValue)
    }

    normalNodes = gameBoard.getChildren.filtered(new Predicate[Node] {
      override def test(node: Node): Boolean = {
        if (node.getId != "Normal Move") return false
        for (move <- moves)
          if (GridPane.getRowIndex(node) == move.getKey && GridPane.getColumnIndex(node) == move.getValue)
            if (player.gameBoard(move.getKey)(move.getValue) == null)
              return true

        false
      }
    })

    attackNodes = gameBoard.getChildren.filtered(new Predicate[Node] {
      override def test(node: Node): Boolean = {
        if (node.getId != "Attack Move") return false
        for (move <- moves)
          if (GridPane.getRowIndex(node) == move.getKey && GridPane.getColumnIndex(node) == move.getValue)
            if (player.gameBoard(move.getKey)(move.getValue) != null)
              return true

        false
      }
    })

    normalNodes.forEach(node => {
      node.setVisible(true)
    })

    attackNodes.forEach(node => {
      node.setVisible(true)
    })
  }

  def hideAvailableMovements(): Unit = {
    normalNodes.forEach(node => {
      node.setVisible(false)
    })

    attackNodes.forEach(node => {
      node.setVisible(false)
    })
  }

  def preparePromotion(player: ChessPlayer): Unit = {
    val buts = player.promButs.getChildren
    buts.forEach(but => but.setOnMousePressed(_ => {
      val x: Int = GridPane.getColumnIndex(but)
      val y: Int = GridPane.getRowIndex(but)
      val ps: ChessPiece = promotionMap(player.color)(new Pair[Int, Int](x, y)).promote(player.newRow, player.newCol)
      player.Movement(ps.image)

      player.gameBoard(player.newRow)(player.newCol) = ps
      player.observer.score(player.color) += ps.rank - 10
      movementDraw(player.src, new State(0, 0, player.newRow, player.newCol, 0), ps.image)

      player.promButs.setVisible(false)
      player.promotion = false
      player.Notify()
    }))
  }

  override def movementDraw(source: Node, state: State, arg: Node): Unit = {
    gameBoard.getChildren.remove(source)
    gameBoard.add(arg, state.newCol, state.newRow)
  }
}
