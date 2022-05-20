package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane

class CheckersController extends Controller {
  var board = Array(
    Array("A","B","C","D","E","F","G","H"),
    Array("-", "y", "-","y","-","y","-","y","1"),
    Array("y", ".", "y",".","y",".","y",".","2"),
    Array(".", "y", ".","y",".","y",".","y","3"),
    Array("-", ".", "-",".","-",".","-",".","4"),
    Array(".", "-", ".","-",".","-",".","-","5"),
    Array("x", ".", "x",".","x",".","x",".","6"),
    Array(".", "x", ".","x",".","x",".","x","7"),
    Array("x", ".", "x",".","x",".","x",".","8")
  )
 override def movement(board: GridPane): Unit = {
    board.setOnMouseDragged(e=>{
      val source = (e.getTarget.asInstanceOf[Node])
      //  Draggable(source)
      val colIndex = GridPane.getColumnIndex(source)
      val rowIndex = GridPane.getRowIndex(source)
      System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue, rowIndex.intValue)
    })
  }
}
