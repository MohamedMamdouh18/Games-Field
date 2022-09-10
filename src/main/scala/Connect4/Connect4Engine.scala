package Connect4

import Base.Player.Player
import Base.{GameEngine, Piece, State}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class Connect4Engine(players: Array[Player], gameType: String) extends GameEngine(players, gameType) {
  override val gameController = new Connect4Controller
  override val gameDrawer = new Connect4Drawer
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](6, 7)
  override var score: Array[Int] = Array(0, 0)

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (players(turn).isInstanceOf[Connect4AI]) return
      if (gameBoard(5 - GridPane.getRowIndex(source))(GridPane.getColumnIndex(source)) == null) {
        val validation = gameController.movementValidation(gameBoard,
          new State(0, GridPane.getColumnIndex(source), 0, 0, turn))
        if (validation.valid) {
          val s: State = new State(validation.state.oldRow, GridPane.getColumnIndex(source), 0, 0, turn)
          gameDrawer.movementDraw(source, s)
          update()
        }
      }
    })
  }

  override def play(): Unit ={
    if (players(turn).isInstanceOf[Connect4AI] && !gameEnded) {
      players(turn).Movement()
    }
  }
}
