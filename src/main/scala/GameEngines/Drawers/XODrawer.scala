package GameEngines.Drawers

import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color

class XODrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: Node => Unit = _

  override def draw(): GridPane = {
    val board = drawBoard(3, 3, Color.rgb(236, 205, 153), Color.rgb(236, 205, 153), showGridLines = true)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Node) => Unit): Unit = {

  }
}
