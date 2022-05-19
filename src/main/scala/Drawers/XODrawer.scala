package Drawers
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class XODrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()

  override def draw(): Unit = {
    drawBoard(3, 3, Color.rgb(236, 205, 153), Color.rgb(236, 205, 153), showGridLines = true)
  }

  override def extendDrawing(): Unit = {

  }
}
