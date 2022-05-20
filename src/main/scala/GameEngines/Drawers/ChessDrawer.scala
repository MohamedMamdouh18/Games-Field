package GameEngines.Drawers

import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color

class ChessDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Node) => Unit = _
  override var gameBoard: Array[Array[String]] = _

  override def draw(): GridPane = {
    val board = drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Node) => Unit): Unit = {

  }
}
