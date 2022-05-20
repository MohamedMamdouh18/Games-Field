package GameEngines.Drawers

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

abstract class Drawer {
  var gamePane: StackPane
  var drag: (Node) => Unit

  def drawBoard(rows: Int, cols: Int, color1: Color, color2: Color, showGridLines: Boolean): GridPane = {
    val board = new GridPane
    for (i <- 0 until rows) {
      if (i % 2 == 0) {
        for (j <- 0 until cols) {
          val rect = new Rectangle(80, 80)
          if (j % 2 == 0) rect.setFill(color1)
          else rect.setFill(color2)
          GridPane.setColumnIndex(rect,j)
          GridPane.setRowIndex(rect,i)

          board.add(rect, j, i, 1, 1)
        }
      } else {
        for (j <- 0 until cols) {
          val rect = new Rectangle(80, 80)
          if (j % 2 != 0) rect.setFill(color1)
          else rect.setFill(color2)
          GridPane.setColumnIndex(rect,j)
          GridPane.setRowIndex(rect,i)

           board.add(rect, j, i, 1, 1)
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

  def draw(): GridPane = ???

  def extendDrawing(board: GridPane, Draggable: (Node) => Unit): Unit = ???

  def movementDraw(board: GridPane): Unit = ???

  def setGamePane(newGamePane: StackPane): Unit = {
    gamePane = newGamePane
  }

  def setDrag(dragFn: (Node) => Unit): Unit = {
    drag = dragFn
  }
}
