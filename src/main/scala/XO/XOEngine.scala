package XO

import Base.Player.{ConcretePlayer, Player}
import Base.{GameEngine, Piece, State}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class XOEngine(players: Array[Player], gameType: String) extends GameEngine(players, gameType) {
  override val gameController = new XOController
  override val gameDrawer = new XODrawer
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](3, 3)
  override var score: Array[Int] = Array(0, 0)

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      val s: State = new State(GridPane.getRowIndex(source), GridPane.getColumnIndex(source), 0, 0, turn)
      val check = gameController.movementValidation(gameBoard, s)
      if (check.valid && !gameEnded) {
        gameDrawer.movementDraw(source, s)
        update()
      }
    })
  }

  override def play(): Unit = {
    if (!players(1 - turn).isInstanceOf[ConcretePlayer] && players(turn).isInstanceOf[XOAI])
      players(1 - turn).DisableMovement()

    if (players(turn).isInstanceOf[ConcretePlayer] && players(1 - turn).isInstanceOf[XOAI])
      gameDrawer.setEvents(Movement)

    if (players(turn).isInstanceOf[XOAI])
      players(turn).Movement()
  }
}
