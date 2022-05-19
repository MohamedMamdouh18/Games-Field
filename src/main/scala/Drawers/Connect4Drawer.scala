package Drawers
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class Connect4Drawer extends Drawer {
  override var gamePane: StackPane = new StackPane()

  override def draw(): Unit = {
    drawBoard(6, 7, Color.STEELBLUE, Color.STEELBLUE, showGridLines = false)
  }

  override def extendDrawing(): Unit = {

  }
}
