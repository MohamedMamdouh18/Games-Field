package GameEngines.GamesControllers

import javafx.geometry.{HPos, VPos}
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontWeight}

class XOController extends Controller {
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](3, 3)
  var turn = true

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source), 0, 0)) {
        val text = new Label()
        text.setFont(Font.font("Roboto", FontWeight.BOLD, 50))
        if (this.turn) {
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
    })
  }

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    if (gameBoard(oldCol)(oldRow) == ".") {
      if (this.turn)
        gameBoard(oldCol)(oldRow) = "X"
      else
        gameBoard(oldCol)(oldRow) = "O"

      this.turn = !this.turn

      true
    } else
      false
  }
}
