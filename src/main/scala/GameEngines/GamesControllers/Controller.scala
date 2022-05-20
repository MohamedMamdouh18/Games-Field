package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle

abstract class Controller {
  var board: GridPane = new GridPane()
  var x, y: Double = 0
  var oldRow, oldCol: Int = 0
 var color:String=""
  def Draggable(source: Circle): Unit = {
    if(source.getFill.toString=="0xffffffff")
      {
        color="white"
      }
    else if(source.getFill.toString=="0x000000ff")
      {
        color="black"
      }

    source.setOnMousePressed(e => {
      //val source = e.getTarget.asInstanceOf[Node]
      oldCol = GridPane.getColumnIndex(source)
      oldRow = GridPane.getRowIndex(source)
      x = e.getSceneX - source.getTranslateX
      y = e.getSceneY - source.getTranslateY
    })
    source.setOnMouseDragged(e => {
      source.setTranslateX(e.getSceneX - x)
      source.setTranslateY(e.getSceneY - y)
    })
    println(color)
    source.setOnMouseReleased(e => {
      val source = e.getTarget.asInstanceOf[Node]
      println(board.localToScene(source.getBoundsInLocal))
      println(Math.floor((e.getSceneY - 100) / 80).toInt)
      println(Math.floor((e.getSceneX - 220) / 80).toInt)
      if (movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt,color)) {
        println(Math.floor((e.getSceneY - 100) / 80).toInt)
        println(Math.floor((e.getSceneX - 220) / 80).toInt)
        GridPane.setColumnIndex(source, Math.floor((e.getSceneX - 220) / 80).toInt)
        GridPane.setRowIndex(source,Math.floor((e.getSceneY - 100) / 80).toInt )
      } else {
        println(oldRow)
        println(oldCol)

        GridPane.setRowIndex(source, oldRow+1)
        GridPane.setColumnIndex(source, oldCol+1)

      }
    })
  }

  def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int,color:String): Boolean = ???

  def setBoard(board: GridPane): Unit = {
    this.board = board
  }
}
