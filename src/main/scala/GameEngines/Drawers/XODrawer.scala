package GameEngines.Drawers

import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.{Circle, Rectangle}

class XODrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Node) => Unit = _
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](3, 3)

  override def draw(): GridPane = {
    val board = drawBoard(3, 3, Color.rgb(236, 205, 153), Color.rgb(236, 205, 153), showGridLines = true)
    extendDrawing(board, drag)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Node) => Unit): Unit = {
    board.getChildren.forEach(node => {
      if (node.isInstanceOf[Node]) {
        Draggable(node)
        println("Draggable")
      }
    })
  }
}
