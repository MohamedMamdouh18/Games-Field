package XO

import Base.Player.Player
import Base.{GameEngine, Piece, State}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class XOEngine(player1: Player, player2: Player, gameType: String) extends GameEngine(player1, player2, gameType) {
  override val gameController = new XOController
  override val gameDrawer = new XODrawer
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](3, 3)

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      val s: State = new State(GridPane.getRowIndex(source), GridPane.getColumnIndex(source), 0, 0, turn)
      if (gameController.movementValidation(gameBoard, s).valid) {
        gameDrawer.movementDraw(source, s)
        update()
      }
    })
  }
}
