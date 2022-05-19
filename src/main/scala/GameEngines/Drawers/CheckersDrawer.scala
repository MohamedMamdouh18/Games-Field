package GameEngines.Drawers

import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color

class CheckersDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()

  override def draw(): Unit = {
    val board = drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
  }

  override def extendDrawing(board:GridPane): Unit = {

  }
}
