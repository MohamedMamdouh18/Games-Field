package XO

import Base.{Drawer, Piece, State}
import javafx.geometry.{HPos, VPos}
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontWeight}

object XODrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var gameBoard: GridPane = new GridPane()

  override def drawPiece(): Unit = {
    gameBoard = drawBoard(3, 3, Color.rgb(236, 205, 153), Color.rgb(236, 205, 153), showGridLines = true)
  }

  override def setEvents(Event: Node => Unit, board: Array[Array[Piece]], s: Int, e: Int): Unit = {
    gameBoard.getChildren.forEach(node => {
      if (node.isInstanceOf[Node]) {
        Event(node)
      }
    })
  }

  override def movementDraw(source: Node, state: State, arg: Node): Unit = {
    val text = new Label()
    text.setFont(Font.font("Roboto", FontWeight.BOLD, 50))
    if (state.turn == 1) {
      text.setTextFill(Color.rgb(200, 50, 0, 1))
      text.setText("O")
    } else {
      text.setTextFill(Color.rgb(0, 50, 200, 1))
      text.setText("X")
    }
    gameBoard.add(text, GridPane.getColumnIndex(source), GridPane.getRowIndex(source))
    GridPane.setHalignment(text, HPos.CENTER)
    GridPane.setValignment(text, VPos.CENTER)
  }
}
