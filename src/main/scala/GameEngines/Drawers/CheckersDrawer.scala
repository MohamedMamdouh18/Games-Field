package GameEngines.Drawers
import javafx.geometry.{HPos, VPos}
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
class CheckersDrawer extends Drawer {

  var board2 = Array(
    Array("x", ".", "x",".","x",".","x","."),
    Array(".", "x", ".","x",".","x",".","x"),
    Array("x", ".", "x",".","x",".","x","."),
    Array("-", ".", "-",".","-",".","-","."),
    Array(".", "-", ".","-",".","-",".","-"),
    Array("-", "y", "-","y","-","y","-","y"),
    Array("y", ".", "y",".","y",".","y","."),
    Array(".", "y", ".","y",".","y",".","y"),
  )
  var x,y:Double=0
  override var gamePane: StackPane = new StackPane()
  override def draw(): GridPane = {

    val board = drawBoard(8, 8, Color.rgb(152, 68, 32), Color.rgb(236, 205, 153), showGridLines = false)
    board
  }

  override def extendDrawing(board:GridPane): Unit = {

    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        val circle = new Circle(30)
        if (board2(i)(j)=="x") {

          circle.setFill(Color.rgb(0, 0, 0))
          board.add(circle, j, i, 1, 1)
          GridPane.setColumnIndex(circle,j)
          GridPane.setRowIndex(circle,i)
          Draggable(circle)

        }
        else if(board2(i)(j)=="y") {
          circle.setFill(Color.rgb(255, 255, 255))
          board.add(circle, j, i, 1, 1)
          GridPane.setColumnIndex(circle,j)
          GridPane.setRowIndex(circle,i)
          Draggable(circle)
          println(GridPane.getRowIndex(circle))
        }
        GridPane.setHalignment(circle, HPos.CENTER)
        GridPane.setValignment(circle, VPos.CENTER)
      }
    }
  }
  override def movementDraw(board: GridPane): Unit = {
     board.setOnMouseDragged(e=>{
       val source = (e.getTarget.asInstanceOf[Node])
     //  Draggable(source)
       val colIndex = GridPane.getColumnIndex(source)
       val rowIndex = GridPane.getRowIndex(source)
     System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue, rowIndex.intValue)
     })
  }
  def Draggable(node:Node): Unit =
  {
     node.setOnMousePressed(e=>{
       x=e.getSceneX-node.getTranslateX()
       y=e.getSceneY-node.getTranslateY()
     })
     node.setOnMouseDragged(e=>{
       node.setTranslateX(e.getSceneX()-x)
       node.setTranslateY(e.getSceneY()-y)
     })
  }
}
