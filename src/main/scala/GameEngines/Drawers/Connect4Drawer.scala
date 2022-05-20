package GameEngines.Drawers

import javafx.geometry.{HPos, Pos, VPos}
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import scalafx.scene.input.MouseEvent

class Connect4Drawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Circle) => Unit = _
  override var gameBoard = Array(
    Array("A", "B", "C", "D", "E", "F", "G", "H"),
    Array("y", ".", "y", ".", "y", ".", "y", ".", "1"),
    Array(".", "y", ".", "y", ".", "y", ".", "y", "2"),
    Array("y", ".", "y", ".", "y", ".", "y", ".", "3"),
    Array("-", ".", "-", ".", "-", ".", "-", ".", "4"),
    Array(".", "-", ".", "-", ".", "-", ".", "-", "5"),
    Array("-", "x", "-", "x", "-", "x", "-", "x", "6"),
    Array("x", ".", "x", ".", "x", ".", "x", ".", "7"),
    Array(".", "x", ".", "x", ".", "x", ".", "x", "8"),
  )

  override def draw(): GridPane = {
    val board = drawBoard(6, 7, Color.rgb(0, 0, 139), Color.rgb(0, 0, 139), showGridLines = false)
    extendDrawing(board, drag)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Circle) => Unit): Unit = {
    board.setAlignment(Pos.CENTER)

    for (i <- 0 until 6) {
      for (j <- 0 until 7) {
        val circle = new Circle(35)
        if (j % 2 == 0 && i % 2 == 0) circle.setFill(Color.rgb(164, 43, 57))
        else if (j % 2 == 0 && i % 2 == 1) circle.setFill(Color.rgb(255, 221, 99))
        else circle.setFill(Color.rgb(49, 46, 43))
        circle.setOnMouseClicked(e =>
          Draggable(circle)
        )
        GridPane.setColumnIndex(circle, j)
        GridPane.setRowIndex(circle, i)
        board.add(circle, j, i, 1, 1)
        GridPane.setHalignment(circle, HPos.CENTER)
        GridPane.setValignment(circle, VPos.CENTER)
      }
    }
  }

  def extendDrawing(board: GridPane, Draggable: (Circle) => Unit, boardControl: Array[Array[Int]]): Unit = {
    board.setAlignment(Pos.CENTER)

    for (i <- 0 until 6) {
      for (j <- 0 until 7) {
        val circle = new Circle(35)
        if (boardControl(i)(j) == 1) circle.setFill(Color.rgb(164, 43, 57))
        else if (boardControl(i)(j) == 2) circle.setFill(Color.rgb(255, 221, 99))
        else {
          circle.setFill(Color.rgb(49, 46, 43))
          circle.setOnMouseClicked(e =>
            Draggable(circle)
          )
        }
        GridPane.setColumnIndex(circle, j)
        GridPane.setRowIndex(circle, i)
        board.add(circle, j, i, 1, 1)
        GridPane.setHalignment(circle, HPos.CENTER)
        GridPane.setValignment(circle, VPos.CENTER)
      }
    }
  }

  /*
  def redraw (boardControl: Array[Array[Int]]): Unit =
  {
    for (i <- 0 until 6) {
      for (j <- 0 until 7) {
        val circle = new Circle(35)
        if (j % 2 == 0 && i % 2 == 0) circle.setFill(Color.rgb(164, 43, 57))
        else if (j % 2 == 0 && i % 2 == 1) circle.setFill(Color.rgb(255, 221, 99))
        else circle.setFill(Color.rgb(49, 46, 43))
        circle.setOnMouseClicked(e=>
          Draggable(circle)
        )
        GridPane.setColumnIndex(circle, j)
        GridPane.setRowIndex(circle, i)
        board.add(circle, j, i, 1, 1)
        GridPane.setHalignment(circle, HPos.CENTER)
        GridPane.setValignment(circle, VPos.CENTER)
      }
    }
  }
  */
  def handleEvent(e: MouseEvent) = {
    print("marwan pabloooo")

  }

  override def movementDraw(board: GridPane): Unit = {

  }
}
