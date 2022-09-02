package Chess

import Base.{Drawer, Piece, State}
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color

class ChessDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var gameBoard: GridPane = new GridPane()
  override var drag: Node => Unit = _
  var oldCol, oldRow: Int = 0
  var x, y: Double = 0

  override def draw(board: Array[Array[Piece]]): Unit = {
    gameBoard = drawBoard(8, 8,
      Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    extendDrawing2(board, drag)
  }

  override def extendDrawing2(board: Array[Array[Piece]], Draggable: Node => Unit): Unit = {
    gameBoard.setAlignment(Pos.CENTER)
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        if (board(i)(j) != null) {
          gameBoard.add(board(i)(j).image, j, i)
          Draggable(board(i)(j).image)
        }
      }
    }
  }

  override def movementDraw(source: Node, e: MouseEvent, state: State): Unit = {
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
