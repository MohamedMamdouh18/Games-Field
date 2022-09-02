package XO

import Base.{Drawer, State}
import javafx.geometry.{HPos, VPos}
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontWeight}

class XODrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var gameBoard: GridPane = new GridPane()
  override var drag: Node => Unit = _

  override def draw(): Unit = {
    gameBoard = drawBoard(3, 3, Color.rgb(236, 205, 153), Color.rgb(236, 205, 153), showGridLines = true)
    extendDrawing1(gameBoard, drag)
  }

  override def extendDrawing1(board: GridPane, Draggable: Node => Unit): Unit = {
    board.getChildren.forEach(node => {
      if (node.isInstanceOf[Node]) {
        Draggable(node)
      }
    })
  }

  override def movementDraw(source: Node, e: MouseEvent, state: State): Unit = {
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
