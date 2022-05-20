package GameEngines.Drawers

import GameEngines.Engines.ChessEngine
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color

class ChessDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: Node => Unit = _
  override var gameBoard: Array[Array[String]] = _
  var oldCol, oldRow: Int = 0
  var x, y: Double = 0

  override def draw(): GridPane = {
    val board = drawBoard(8, 8,
      Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    extendDrawing(board, drag)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: Node => Unit): Unit = {
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

  override def movementDraw(board: GridPane, source: Node, e: MouseEvent, turn: Boolean): Unit = {
    if (e.getEventType == MouseEvent.MOUSE_PRESSED) {
      oldCol = GridPane.getColumnIndex(source)
      oldRow = GridPane.getRowIndex(source)
      x = e.getSceneX
      y = e.getSceneY
      println(x)
      println(source.getTranslateX)
    } else if (e.getEventType == MouseEvent.MOUSE_DRAGGED) {
      //      source.setTranslateX(e.getSceneX - x)
      //      source.setTranslateY(e.getSceneY - y)
    } else if (e.getEventType == MouseEvent.MOUSE_RELEASED) {
      //      if (movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
      //        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt)) {
      //        GridPane.setColumnIndex(source, Math.floor((e.getSceneX - 220) / 80).toInt)
      //        GridPane.setRowIndex(source, Math.floor((e.getSceneY - 100) / 80).toInt)
      //        source.setTranslateX(0)
      //        source.setTranslateY(0)
      //
      //      } else {
      //        GridPane.setRowIndex(source, oldRow)
      //        GridPane.setColumnIndex(source, oldCol)
      //        source.setTranslateX(0)
      //        source.setTranslateY(0)
      //      }
    }
  }
}
