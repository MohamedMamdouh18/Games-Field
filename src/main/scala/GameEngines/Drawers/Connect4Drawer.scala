package GameEngines.Drawers

import javafx.geometry.{HPos, Pos, VPos}
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class Connect4Drawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Circle) => Unit = _

  override def draw(): GridPane = {
    val board = drawBoard(6, 7, Color.rgb(0, 0, 139), Color.rgb(0, 0, 139), showGridLines = false)
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
        GridPane.setColumnIndex(circle, j)
        GridPane.setRowIndex(circle, i)
        board.add(circle, j, i, 1, 1)
        GridPane.setHalignment(circle, HPos.CENTER)
        GridPane.setValignment(circle, VPos.CENTER)

      }
    }
  }

  override def movementDraw(board: GridPane): Unit = {

  }
}
