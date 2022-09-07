package Chess

import Base.Player.Player
import Base.{GameEngine, Piece}
import Chess.Pieces._
import javafx.scene.layout.{GridPane, StackPane}

class ChessEngine(player1: Player, player2: Player, gameType: String) extends GameEngine(player1, player2, gameType) {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[Piece]] = Array(
    Array(
      new Rook(ChessPieceEn.BlackRook, 0, 0, 1),
      new Knight(ChessPieceEn.BlackKnight, 0, 1, 1),
      new Bishop(ChessPieceEn.BlackBishop, 0, 2, 1),
      new Queen(ChessPieceEn.BlackQueen, 0, 3, 1),
      new King(ChessPieceEn.BlackKing, 0, 4, 1),
      new Bishop(ChessPieceEn.BlackBishop, 0, 5, 1),
      new Knight(ChessPieceEn.BlackKnight, 0, 6, 1),
      new Rook(ChessPieceEn.BlackRook, 0, 7, 1)),

    Array(
      new Pawn(ChessPieceEn.BlackPawn, 1, 0, 1),
      new Pawn(ChessPieceEn.BlackPawn, 1, 1, 1),
      new Pawn(ChessPieceEn.BlackPawn, 1, 2, 1),
      new Pawn(ChessPieceEn.BlackPawn, 1, 3, 1),
      new Pawn(ChessPieceEn.BlackPawn, 1, 4, 1),
      new Pawn(ChessPieceEn.BlackPawn, 1, 5, 1),
      new Pawn(ChessPieceEn.BlackPawn, 1, 6, 1),
      new Pawn(ChessPieceEn.BlackPawn, 1, 7, 1)),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(
      new Pawn(ChessPieceEn.WhitePawn, 6, 0, 0),
      new Pawn(ChessPieceEn.WhitePawn, 6, 1, 0),
      new Pawn(ChessPieceEn.WhitePawn, 6, 2, 0),
      new Pawn(ChessPieceEn.WhitePawn, 6, 3, 0),
      new Pawn(ChessPieceEn.WhitePawn, 6, 4, 0),
      new Pawn(ChessPieceEn.WhitePawn, 6, 5, 0),
      new Pawn(ChessPieceEn.WhitePawn, 6, 6, 0),
      new Pawn(ChessPieceEn.WhitePawn, 6, 7, 0)),

    Array(
      new Rook(ChessPieceEn.WhiteRook, 7, 0, 0),
      new Knight(ChessPieceEn.WhiteKnight, 7, 1, 0),
      new Bishop(ChessPieceEn.WhiteBishop, 7, 2, 0),
      new Queen(ChessPieceEn.WhiteQueen, 7, 3, 0),
      new King(ChessPieceEn.WhiteKing, 7, 4, 0),
      new Bishop(ChessPieceEn.WhiteBishop, 7, 5, 0),
      new Knight(ChessPieceEn.WhiteKnight, 7, 6, 0),
      new Rook(ChessPieceEn.WhiteRook, 7, 7, 0)),
  )
  override var score: Array[Int] = Array(1290, 1290)
  var whitePromButs: GridPane = _
  var blackPromButs: GridPane = _

  override def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    player2.color = 1
    gameDrawer.drawPiece()
    player1.run(blackPromButs)
    player2.run(whitePromButs)
  }

  override def update(): Unit = {
    turn = 1 - turn
    if(player1.color == turn && player1.isInstanceOf[AIChess]){
      player1.Movement()
    }else if(player2.color == turn && player2.isInstanceOf[AIChess]){
      player2.Movement()
    }
  }

  def setPromButs(buts1: GridPane, buts2: GridPane): Unit = {
    whitePromButs = buts1
    blackPromButs = buts2
  }
}
