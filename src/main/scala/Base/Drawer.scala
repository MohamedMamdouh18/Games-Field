package Base

import javafx.geometry.{HPos, Pos, VPos}
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.{Circle, Rectangle}

abstract class Drawer {
  var gamePane: StackPane
  var gameBoard: GridPane

  def drawBoard(rows: Int, cols: Int, color1: Color, color2: Color, showGridLines: Boolean, showMovements: Boolean = false): GridPane = {
    val board = new GridPane
    for (i <- 0 until rows) {
      if (i % 2 == 0) {
        for (j <- 0 until cols) {
          val rect = new Rectangle(80, 80)

          if (j % 2 == 0) rect.setFill(color1)
          else rect.setFill(color2)

          board.add(rect, j, i)
          if (showMovements)
            showMoves(board, j, i)
        }
      } else {
        for (j <- 0 until cols) {
          val rect = new Rectangle(80, 80)
          if (j % 2 != 0) rect.setFill(color1)
          else rect.setFill(color2)

          board.add(rect, j, i)
          if (showMovements)
            showMoves(board, j, i)
        }
      }
    }

    board.setVisible(true)
    board.setAlignment(Pos.CENTER)
    board.setGridLinesVisible(showGridLines)
    gamePane.setAlignment(Pos.CENTER)
    gamePane.getChildren.add(board)

    board
  }

  private def showMoves(board: GridPane, j: Int, i: Int): Unit = {
    val normalCircle = new Circle(15)
    val attackCircle = new Circle(30)

    normalCircle.setId("Normal Move")
    attackCircle.setId("Attack Move")

    normalCircle.setFill(Color.rgb(54, 69, 79, 0.5))
    attackCircle.setStroke(Color.rgb(54, 69, 79, 0.5))
    attackCircle.setStrokeWidth(10)
    attackCircle.setFill(Color.TRANSPARENT)

    normalCircle.setVisible(false)
    attackCircle.setVisible(false)

    GridPane.setHalignment(normalCircle, HPos.CENTER)
    GridPane.setValignment(normalCircle, VPos.CENTER)
    GridPane.setHalignment(attackCircle, HPos.CENTER)
    GridPane.setValignment(attackCircle, VPos.CENTER)

    board.add(normalCircle, j, i)
    board.add(attackCircle, j, i)
  }

  def drawPiece(): Unit

  def setEvents(Event: Node => Unit,
                board: Array[Array[Piece]] = Array.ofDim[Piece](0, 0), s: Int = 0, e: Int = 0): Unit

  def movementDraw(source: Node, state: State, arg: Node = null): Unit

  def setGamePane(newGamePane: StackPane): Unit = {
    gamePane = newGamePane
  }
}
