package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane

abstract class Controller {
  var board: GridPane = new GridPane()
  var x, y: Double = 0
  var oldRow, oldCol: Int = 0

  def Draggable(node: Node): Unit = {
    node.setOnMousePressed(e => {
      val source = e.getTarget.asInstanceOf[Node]
      oldCol = GridPane.getColumnIndex(source)
      oldRow = GridPane.getRowIndex(source)
      x = e.getSceneX - node.getTranslateX
      y = e.getSceneY - node.getTranslateY
    })
    node.setOnMouseDragged(e => {
      node.setTranslateX(e.getSceneX - x)
      node.setTranslateY(e.getSceneY - y)
    })

    node.setOnMouseReleased(e => {
      val source = e.getTarget.asInstanceOf[Node]
      println(board.localToScene(source.getBoundsInLocal))
      println(Math.floor((e.getSceneY - 100) / 80).toInt)
      println(Math.floor((e.getSceneX - 220) / 80).toInt)
      if (!movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt)) {
        GridPane.setRowIndex(source, Math.floor((e.getSceneY - 100) / 80).toInt)
        GridPane.setColumnIndex(source, Math.floor((e.getSceneX - 220) / 80).toInt)
      } else {
        GridPane.setRowIndex(source, oldRow)
        GridPane.setColumnIndex(source, oldCol)
      }
    })
  }

  def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = ???

  def setBoard(board: GridPane): Unit = {
    this.board = board
  }
}
