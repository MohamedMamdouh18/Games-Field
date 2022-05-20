package GameEngines.Drawers

import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class XODrawer extends Drawer {
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
    val board = drawBoard(3, 3, Color.rgb(236, 205, 153), Color.rgb(236, 205, 153), showGridLines = true)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Circle) => Unit): Unit = {

  }
}
