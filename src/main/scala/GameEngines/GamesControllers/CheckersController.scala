package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane

class CheckersController extends Controller {
  var boardData = Array(
    Array("A", "B", "C", "D", "E", "F", "G", "H"),
    Array("-", "y", "-", "y", "-", "y", "-", "y", "1"),
    Array("y", ".", "y", ".", "y", ".", "y", ".", "2"),
    Array(".", "y", ".", "y", ".", "y", ".", "y", "3"),
    Array("-", ".", "-", ".", "-", ".", "-", ".", "4"),
    Array(".", "-", ".", "-", ".", "-", ".", "-", "5"),
    Array("x", ".", "x", ".", "x", ".", "x", ".", "6"),
    Array(".", "x", ".", "x", ".", "x", ".", "x", "7"),
    Array("x", ".", "x", ".", "x", ".", "x", ".", "8")
  )


  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    println(oldCol + " " + oldRow + " " + newCol + " " + newRow)
    true
  }
}
