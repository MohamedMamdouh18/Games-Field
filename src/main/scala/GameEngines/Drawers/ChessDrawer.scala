package GameEngines.Drawers

import GameEngines.Engines.ChessEngine
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color

class ChessDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: (Node) => Unit = _
  override var gameBoard: Array[Array[String]] = _

  override def draw(): GridPane = {
    val board = drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    extendDrawing(board, drag)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: (Node) => Unit): Unit = {
    board.setAlignment(Pos.CENTER)
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        if (ChessEngine.board(i)(j) != null) {
          board.add(ChessEngine.board(i)(j).image, j, i)
          Draggable(ChessEngine.board(i)(j).image)
        }
      }
    }
  }


}
