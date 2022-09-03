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
  override var Event: Node => Unit = _
  var oldCol, oldRow: Int = 0
  var x, y: Double = 0

  override def drawPiece(board: Array[Array[Piece]]): Unit = {
    gameBoard = drawBoard(8, 8,
      Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    extendDrawing2(board, Event)
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

  override def movementDraw(source: Node, e: MouseEvent, state: State,
                            board: Array[Array[Piece]] = Array.ofDim[Piece](0, 0)): Unit = {

  }
}
