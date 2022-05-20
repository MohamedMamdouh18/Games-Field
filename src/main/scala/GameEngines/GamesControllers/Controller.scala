package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle
abstract class Controller {
  var Playerrole:Int=1
  var board: GridPane = new GridPane()
  var x, y: Double = 0
  var oldRow, oldCol: Int = 0

  def Draggable(source: Circle): Unit = {
    source.setOnMousePressed(e => {
      oldCol = GridPane.getColumnIndex(source)
      oldRow = GridPane.getRowIndex(source)
      x = e.getSceneX - source.getTranslateX
      y = e.getSceneY - source.getTranslateY
    })
    source.setOnMouseDragged(e => {
      source.setTranslateX(e.getSceneX - x)
      source.setTranslateY(e.getSceneY - y)
    })
    source.setOnMouseReleased(e => {
     // val source = e.getTarget.asInstanceOf[Node]
      println(board.localToScene(source.getBoundsInLocal))
      println(Math.floor((e.getSceneY - 100) / 80).toInt)
      println(Math.floor((e.getSceneX - 220) / 80).toInt)
      if (movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt)) {
        println(Math.floor((e.getSceneY - 100) / 80).toInt)
        println(Math.floor((e.getSceneX - 220) / 80).toInt)
        GridPane.setColumnIndex(source, Math.floor((e.getSceneX - 220) / 80).toInt)
        GridPane.setRowIndex(source,Math.floor((e.getSceneY - 100) / 80).toInt )
        source.setTranslateX(0)
        source.setTranslateY(0)

      } else {
        println(oldRow)
        println(oldCol)
        GridPane.setRowIndex(source, oldRow)
        GridPane.setColumnIndex(source, oldCol)
        source.setTranslateX(0)
        source.setTranslateY(0)

      }
    })
  }

  def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = ???

  def setBoard(board: GridPane): Unit = {
    this.board = board
  }
}
