package Engines

import Controllers.XOController
import Drawers.XODrawer
import javafx.scene.Node
import javafx.scene.layout.GridPane

class XOEngine extends GameEngine {
  override val gameController = new XOController
  override val gameDrawer = new XODrawer
  override var gameBoard: Array[Array[String]] = Array(
    Array(".", ".", "."),
    Array(".", ".", "."),
    Array(".", ".", ".")
  )

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (gameController.movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source), 0, 0)) {
        gameDrawer.movementDraw(gameController.board, source, null, gameController.turn)
      }
    })
  }

}
