package Chess

import Base.Player.Player
import Base.{GameEngine, Piece}
import Chess.Pieces._
import javafx.scene.layout.{GridPane, StackPane}

class ChessEngine(players: Array[Player], gameType: String) extends GameEngine(players, gameType) {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[Piece]] = Array(
    Array(
      null,
      null,
      null,
      null,
      new King(ChessEn.BlackKing, 0, 4, 1),
      null,
      null,
      null),

    Array(
      new Pawn(ChessEn.BlackPawn, 1, 0, 1),
      new Pawn(ChessEn.BlackPawn, 1, 1, 1),
      new Pawn(ChessEn.BlackPawn, 1, 2, 1),
      new Pawn(ChessEn.BlackPawn, 1, 3, 1),
      new Pawn(ChessEn.BlackPawn, 1, 4, 1),
      new Pawn(ChessEn.BlackPawn, 1, 5, 1),
      new Pawn(ChessEn.BlackPawn, 1, 6, 1),
      new Pawn(ChessEn.BlackPawn, 1, 7, 1)),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(
      new Pawn(ChessEn.WhitePawn, 6, 0, 0),
      new Pawn(ChessEn.WhitePawn, 6, 1, 0),
      new Pawn(ChessEn.WhitePawn, 6, 2, 0),
      new Pawn(ChessEn.WhitePawn, 6, 3, 0),
      new Pawn(ChessEn.WhitePawn, 6, 4, 0),
      new Pawn(ChessEn.WhitePawn, 6, 5, 0),
      new Pawn(ChessEn.WhitePawn, 6, 6, 0),
      new Pawn(ChessEn.WhitePawn, 6, 7, 0)),

    Array(
      null,
      null,
      null,
      null,
      new King(ChessEn.WhiteKing, 7, 4, 0),
      null,
      null,
      null)
  )
  override var score: Array[Int] = Array(1290, 1290)
  var whitePromButs: GridPane = _
  var blackPromButs: GridPane = _

  override def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    players(1 - turn).color = ChessEn.Black
    gameDrawer.drawPiece()
    players(ChessEn.Black).run(blackPromButs)
    players(ChessEn.White).run(whitePromButs)
    play()
  }

  override def play(): Unit = {
    if (players(turn).isInstanceOf[ChessAI] && !gameEnded)
      players(turn).Movement()
  }

  override def update(): Unit = {
    turn = 1 - turn

    if (gameController.checkEndGame(gameBoard, turn)) {
      gameEnded = true
      if (gameController.checkMate(gameBoard, turn))
        gameDrawer.drawEnd(1 - turn)
      else
        gameDrawer.drawEnd(-1)
      return
    }

    if (gameController.checkMate(gameBoard, turn))
      gameController.findKing(gameBoard, turn).checked = true
    else
      gameController.findKing(gameBoard, turn).checked = false

    play()
  }

  def setPromButs(buts1: GridPane, buts2: GridPane): Unit = {
    whitePromButs = buts1
    blackPromButs = buts2
  }

}
