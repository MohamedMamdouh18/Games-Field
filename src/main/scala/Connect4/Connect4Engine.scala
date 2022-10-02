package Connect4

import Base.{Controller, GameEngine, Piece, Player, State}
import Chess.{ChessController, ChessPlayer}
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}

class Connect4Engine(players: Array[Player], gameType: String) extends GameEngine(players, gameType) {
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](6, 7)
  override var score: Array[Int] = Array(0, 0)

  override def startGame(gamePane: StackPane): Unit = {
    Connect4Drawer.setGamePane(gamePane)
    players(1).color = 1
    Connect4Drawer.drawPiece()

    players.foreach(player => player.run())
    Connect4Drawer.setEvents(Movement)

    play()
  }

  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(_ => {
      if (players(turn).isInstanceOf[Connect4AI]) return
      if (gameBoard(5 - GridPane.getRowIndex(source))(GridPane.getColumnIndex(source)) == null) {
        val validation = Connect4Controller.movementValidation(gameBoard,
          new State(0, GridPane.getColumnIndex(source), 0, 0, turn))
        if (validation.valid) {
          val s: State = new State(validation.state.oldRow, GridPane.getColumnIndex(source), 0, 0, turn)
          Connect4Drawer.movementDraw(source, s)
          update()
        }
      }
    })
  }

  override def update(): Unit = {
    turn = 1 - turn

    if (Connect4Controller.checkEndGame(gameBoard, 1 - turn)) {
      gameEnded = true
      players.foreach(player => player.DisableMovement())
      Connect4Drawer.drawEnd(1 - turn)
    }

    if (Connect4Controller.checkTie(gameBoard) && !gameEnded) {
      gameEnded = true
      Connect4Drawer.drawEnd(-1)
    }

    play()
  }

  override def play(): Unit = {
    if (players(turn).isInstanceOf[Connect4AI] && !gameEnded) {
      players(turn).Movement()
    }
  }
}
