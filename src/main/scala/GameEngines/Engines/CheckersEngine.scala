package GameEngines.Engines

import GameEngines.Drawers.CheckersDrawer
import GameEngines.GamesControllers.CheckersController
import javafx.scene.Node
import javafx.scene.layout.GridPane

class CheckersEngine extends GameEngine {
  override val gameController = new CheckersController
  override val gameDrawer = new CheckersDrawer
  override var gameBoard: Array[Array[String]] = Array(
    Array("A", "B", "C", "D", "E", "F", "G", "H"),
    Array("y", ".", "y", ".", "y", ".", "y", ".", "1"),
    Array(".", "y", ".", "y", ".", "y", ".", "y", "2"),
    Array("y", ".", "y", ".", "y", ".", "y", ".", "3"),
    Array("-", ".", "-", ".", "-", ".", "-", ".", "4"),
    Array(".", "-", ".", "-", ".", "-", ".", "-", "5"),
    Array("-", "x", "-", "x", "-", "x", "-", "x", "6"),
    Array("x", ".", "x", ".", "x", ".", "x", ".", "7"),
    Array(".", "x", ".", "x", ".", "x", ".", "x", "8"),
  )

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      gameDrawer.movementDraw(gameController.board, source, e, turn = false)
    })
    source.setOnMouseDragged(e => {
      gameDrawer.movementDraw(gameController.board, source, e, turn = false)
    })
    source.setOnMouseReleased(e => {
      if (gameController.movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt)) {
        gameDrawer.movementDraw(gameController.board, source, e, turn = true)
      } else {
        gameDrawer.movementDraw(gameController.board, source, e, turn = false)
      }
    })
  }
}
