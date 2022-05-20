package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane

class ChessController extends Controller {
  override var gameBoard: Array[Array[String]] = _
  var white:Boolean = true

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      oldCol = GridPane.getColumnIndex(source)
      oldRow = GridPane.getRowIndex(source)
      x = e.getSceneX
      y = e.getSceneY
      println(x)
      println(source.getTranslateX)
    })
    source.setOnMouseDragged(e => {
//      source.setTranslateX(e.getSceneX - x)
//      source.setTranslateY(e.getSceneY - y)
    })
    source.setOnMouseReleased(e => {
//      if (movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
//        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt)) {
//        GridPane.setColumnIndex(source, Math.floor((e.getSceneX - 220) / 80).toInt)
//        GridPane.setRowIndex(source, Math.floor((e.getSceneY - 100) / 80).toInt)
//        source.setTranslateX(0)
//        source.setTranslateY(0)
//
//      } else {
//        GridPane.setRowIndex(source, oldRow)
//        GridPane.setColumnIndex(source, oldCol)
//        source.setTranslateX(0)
//        source.setTranslateY(0)
//      }
    })
  }
}
