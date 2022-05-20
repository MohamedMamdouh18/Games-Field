package GameEngines.Drawers

import javafx.geometry.{HPos, VPos}
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class CheckersDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Node) => Unit = _
  override var gameBoard: Array[Array[String]] = _
  var MiniBoard: Array[Array[String]] = Array(
    Array("y", ".", "y", ".", "y", ".", "y", "."),
    Array(".", "y", ".", "y", ".", "y", ".", "y"),
    Array("y", ".", "y", ".", "y", ".", "y", "."),
    Array("-", ".", "-", ".", "-", ".", "-", "."),
    Array(".", "-", ".", "-", ".", "-", ".", "-"),
    Array("-", "x", "-", "x", "-", "x", "-", "x"),
    Array("x", ".", "x", ".", "x", ".", "x", "."),
    Array(".", "x", ".", "x", ".", "x", ".", "x"),
  )

  override def draw(): GridPane = {
    val board = drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    extendDrawing(board, drag)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Node) => Unit): Unit = {

    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        val circle = new Circle(30)
        if (MiniBoard(i)(j) == "x") {
          circle.setFill(Color.rgb(255, 255, 255))
          board.add(circle, j, i)

          Draggable(circle)
        }
        else if (MiniBoard(i)(j) == "y") {
          circle.setFill(Color.rgb(0, 0, 0))
          board.add(circle, j, i)
          Draggable(circle)
        }
        GridPane.setHalignment(circle, HPos.CENTER)
        GridPane.setValignment(circle, VPos.CENTER)
      }
    }
  }

  override def movementDraw(board: GridPane): Unit = {

  }
}
