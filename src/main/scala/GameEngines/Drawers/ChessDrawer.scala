package GameEngines.Drawers

import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class ChessDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Circle) => Unit = _

  override def draw(): GridPane = {
    val board = drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Circle) => Unit): Unit = {

  }
}
