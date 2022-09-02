package Engines

import Base.{Piece, State}
import Controllers.Connect4Controller
import Drawers.Connect4Drawer
import javafx.scene.Node
import javafx.scene.layout.GridPane

class Connect4Engine extends GameEngine {
  override val gameController = new Connect4Controller
  override val gameDrawer = new Connect4Drawer
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](6, 7)

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (gameBoard(5 - GridPane.getRowIndex(source))(GridPane.getColumnIndex(source)) == null) {
        val validation = gameController.movementValidation(gameBoard,
          new State(0, GridPane.getColumnIndex(source), 0, 0, turn))
        if (validation.valid) {
          val s: State = new State(validation.state.oldRow, GridPane.getColumnIndex(source), 0, 0, turn)
          gameDrawer.movementDraw(source, null, s)
          turn = 1 - turn
        }
      }
    })
  }
}
