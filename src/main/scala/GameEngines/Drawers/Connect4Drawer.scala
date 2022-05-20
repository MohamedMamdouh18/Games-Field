package GameEngines.Drawers

import javafx.geometry.{HPos, Pos, VPos}
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class Connect4Drawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Node) => Unit = _
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](6, 7)

  override def draw(): GridPane = {
    val board = drawBoard(6, 7, Color.rgb(0, 0, 139), Color.rgb(0, 0, 139), showGridLines = false)
    extendDrawing(board, drag)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Node) => Unit): Unit = {
    board.setAlignment(Pos.CENTER)

    for (i <- 0 until 6) {
      for (j <- 0 until 7) {
        val circle = new Circle(35)
        if (gameBoard(i)(j) == "1") circle.setFill(Color.rgb(164, 43, 57))
        else if (gameBoard(i)(j) == "2") circle.setFill(Color.rgb(255, 221, 99))
        else {
          circle.setFill(Color.rgb(49, 46, 43))
          Draggable(circle)
        }
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
