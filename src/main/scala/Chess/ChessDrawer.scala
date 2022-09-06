package Chess

import Base.{Drawer, Piece, State}
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color

class ChessDrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var gameBoard: GridPane = new GridPane()
  var oldCol, oldRow: Int = 0
  var x, y: Double = 0

  override def drawPiece(): Unit = {
    gameBoard = drawBoard(8, 8,
      Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
  }

  override def setEvents(Event: Node => Unit, board: Array[Array[Piece]], s: Int, e: Int): Unit = {
    gameBoard.setAlignment(Pos.CENTER)
    for (i <- s to e) {
      for (j <- 0 to 7) {
        if (board(i)(j) != null) {
          gameBoard.add(board(i)(j).image, j, i)
          Event(board(i)(j).image)
        }
      }
    }
  }

  override def movementDraw(source: Node, state: State, arg: Node): Unit = {
    gameBoard.getChildren.remove(source)
    gameBoard.add(arg, state.newCol, state.newRow)
  }
}
