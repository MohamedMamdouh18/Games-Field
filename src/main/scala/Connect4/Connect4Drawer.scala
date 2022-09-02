package Connect4

import Base.{Drawer, State}
import javafx.geometry.{HPos, Pos, VPos}
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import scala.util.control.Breaks.{break, breakable}

class Connect4Drawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var gameBoard: GridPane = new GridPane()
  override var drag: Node => Unit = _

  override def draw(): Unit = {
    gameBoard = drawBoard(6, 7, Color.rgb(0, 0, 139), Color.rgb(0, 0, 139), showGridLines = false)
    extendDrawing1(gameBoard, drag)
  }

  override def extendDrawing1(board: GridPane, Draggable: Node => Unit): Unit = {
    board.setAlignment(Pos.CENTER)

    for (i <- 0 until 6) {
      for (j <- 0 until 7) {
        val circle = new Circle(35)
        circle.setFill(Color.rgb(49, 46, 43))
        Draggable(circle)
        GridPane.setColumnIndex(circle, j)
        GridPane.setRowIndex(circle, i)
        board.add(circle, j, i, 1, 1)
        GridPane.setHalignment(circle, HPos.CENTER)
        GridPane.setValignment(circle, VPos.CENTER)
      }
    }
  }

  override def movementDraw(source: Node, e: MouseEvent, state: State): Unit = {
    breakable {
      gameBoard.getChildren.forEach {
        case node@(x: Circle) if GridPane.getColumnIndex(node) == state.oldCol
          && GridPane.getRowIndex(node) == 5 - state.oldRow =>
          if (state.turn == 1) x.setFill(Color.rgb(164, 43, 57))
          else x.setFill(Color.rgb(255, 221, 99))
          break
        case _ =>
      }
    }
  }
}