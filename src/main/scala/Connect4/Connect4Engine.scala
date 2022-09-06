package Connect4

import Base.{GameEngine, Piece, Player, State}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class Connect4Engine(player1: Player, player2: Player, gameType: String) extends GameEngine(player1, player2, gameType) {
  override val gameController = new Connect4Controller
  override val gameDrawer = new Connect4Drawer
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](6, 7)

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (gameBoard(5 - GridPane.getRowIndex(source))(GridPane.getColumnIndex(source)) == null) {
        val validation = gameController.movementValidation(gameBoard,
          new State(0, GridPane.getColumnIndex(source), 0, 0, turn(0)))
        if (validation.valid) {
          val s: State = new State(validation.state.oldRow, GridPane.getColumnIndex(source), 0, 0, turn(0))
          gameDrawer.movementDraw(source, s)
          turn(0) = 1 - turn(0)
        }
      }
    })
  }
}
