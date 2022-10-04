package XO

import Base.{GameEngine, Piece, Player, State}
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}

class XOEngine(players: Array[Player], gameType: String) extends GameEngine(players, gameType) {
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](3, 3)
  override var score: Array[Int] = Array(0, 0)

  override def startGame(gamePane: StackPane): Unit = {
    XODrawer.setGamePane(gamePane)
    players(1).color = 1
    XODrawer.drawPiece()

    players.foreach(player => player.run())
    XODrawer.setEvents(Movement)

    play()
  }

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (players(turn).isInstanceOf[XOAI]) return
      val s: State = new State(GridPane.getRowIndex(source), GridPane.getColumnIndex(source), 0, 0, turn)
      val check = XOController.movementValidation(gameBoard, s)
      if (check.valid && !gameEnded) {
        XODrawer.movementDraw(source, s)
        update()
      }
    })
  }

  override def update(): Unit = {
    turn = 1 - turn

    if (XOController.checkEndGame(gameBoard, 1 - turn)) {
      gameEnded = true
      players.foreach(player => player.DisableMovement())
      XODrawer.drawEnd(1 - turn)
    }

    if (XOController.checkTie(gameBoard) && !gameEnded) {
      gameEnded = true
      XODrawer.drawEnd(-1)
    }

    play()
  }

  override def play(): Unit = {
    if (players(turn).isInstanceOf[XOAI] && !gameEnded)
      players(turn).Movement()
  }
}
