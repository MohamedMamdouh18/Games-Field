package Base

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

abstract class Drawer {
  var gamePane: StackPane
  var gameBoard: GridPane
  var Event: Node => Unit

  def drawBoard(rows: Int, cols: Int, color1: Color, color2: Color, showGridLines: Boolean): GridPane = {
    val board = new GridPane
    for (i <- 0 until rows) {
      if (i % 2 == 0) {
        for (j <- 0 until cols) {
          val rect = new Rectangle(80, 80)
          if (j % 2 == 0) rect.setFill(color1)
          else rect.setFill(color2)

          board.add(rect, j, i)
        }
      } else {
        for (j <- 0 until cols) {
          val rect = new Rectangle(80, 80)
          if (j % 2 != 0) rect.setFill(color1)
          else rect.setFill(color2)

          board.add(rect, j, i)
        }
      }
    }

    board.setVisible(true)
    board.setAlignment(Pos.CENTER)
    board.setGridLinesVisible(showGridLines)
    gamePane.setAlignment(Pos.CENTER)
    gamePane.getChildren.add(board)

    board
  }

  def drawPiece(board: Array[Array[Piece]] = Array.ofDim[Piece](0, 0)): Unit

  def extendDrawing1(Event: Node => Unit): Unit = {

  }

  def extendDrawing2(board: Array[Array[Piece]], Draggable: Node => Unit): Unit = {

  }

  def movementDraw(source: Node, e: MouseEvent, state: State,
                   board: Array[Array[Piece]] = Array.ofDim[Piece](0, 0)): Unit

  def setGamePane(newGamePane: StackPane): Unit = {
    gamePane = newGamePane
  }

  def setEvent(EventFn: Node => Unit): Unit = {
    Event = EventFn
  }
}
