package Engines

import Controllers.Connect4Controller
import Drawers.Connect4Drawer
import javafx.scene.Node
import javafx.scene.layout.GridPane

class Connect4Engine extends GameEngine {
  override val gameController = new Connect4Controller
  override val gameDrawer = new Connect4Drawer
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](6, 7)

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (gameBoard(5 - GridPane.getRowIndex(source))(GridPane.getColumnIndex(source)) == null) {
        gameController.movementValidation(GridPane.getColumnIndex(source), 0, 0, 0)
      }
    })
  }
}
