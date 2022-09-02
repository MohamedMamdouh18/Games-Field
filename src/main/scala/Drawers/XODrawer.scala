package Drawers

import javafx.geometry.{HPos, VPos}
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontWeight}

class XODrawer extends Drawer {
  override var gamePane: StackPane = new StackPane()
  override var drag: Node => Unit = _
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](3, 3)

  override def draw(): GridPane = {
    val board = drawBoard(3, 3, Color.rgb(236, 205, 153), Color.rgb(236, 205, 153), showGridLines = true)
    extendDrawing(board, drag)
    board
  }

  override def extendDrawing(board: GridPane, Draggable: Node => Unit): Unit = {
    board.getChildren.forEach(node => {
      if (node.isInstanceOf[Node]) {
        Draggable(node)
      }
    })
  }

  override def movementDraw(board: GridPane, source: Node, e: MouseEvent, turn: Boolean): Unit = {
    val text = new Label()
    text.setFont(Font.font("Roboto", FontWeight.BOLD, 50))
    if (turn) {
      text.setTextFill(Color.rgb(200, 50, 0, 1))
      text.setText("O")
    } else {
      text.setTextFill(Color.rgb(0, 50, 200, 1))
      text.setText("X")
    }
    board.add(text, GridPane.getColumnIndex(source), GridPane.getRowIndex(source))
    GridPane.setHalignment(text, HPos.CENTER)
    GridPane.setValignment(text, VPos.CENTER)
  }
}
