package Chess

import Base.{GameEngine, Piece, Player, State}
import Chess.Pieces._
import javafx.scene.Node
import javafx.scene.layout.{GridPane, StackPane}

class ChessEngine(players: Array[Player], gameType: String) extends GameEngine(players, gameType) {
  override var gameBoard: Array[Array[Piece]] = Array(
    Array(
      new Rook(ChessEn.BlackRook, 0, 0, 1),
      new Knight(ChessEn.BlackKnight, 0, 1, 1),
      new Bishop(ChessEn.BlackBishop, 0, 2, 1),
      new Queen(ChessEn.BlackQueen, 0, 3, 1),
      new King(ChessEn.BlackKing, 0, 4, 1),
      new Bishop(ChessEn.BlackBishop, 0, 5, 1),
      new Knight(ChessEn.BlackKnight, 0, 6, 1),
      new Rook(ChessEn.BlackRook, 0, 7, 1)
    ),

    Array(
      new Pawn(ChessEn.BlackPawn, 1, 0, 1),
      new Pawn(ChessEn.BlackPawn, 1, 1, 1),
      new Pawn(ChessEn.BlackPawn, 1, 2, 1),
      new Pawn(ChessEn.BlackPawn, 1, 3, 1),
      new Pawn(ChessEn.BlackPawn, 1, 4, 1),
      new Pawn(ChessEn.BlackPawn, 1, 5, 1),
      new Pawn(ChessEn.BlackPawn, 1, 6, 1),
      new Pawn(ChessEn.BlackPawn, 1, 7, 1)
    ),

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
      new Pawn(ChessEn.WhitePawn, 6, 7, 0)
    ),

    Array(
      new Rook(ChessEn.WhiteRook, 7, 0, 0),
      new Knight(ChessEn.WhiteKnight, 7, 1, 0),
      new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
      new Queen(ChessEn.WhiteQueen, 7, 3, 0),
      new King(ChessEn.WhiteKing, 7, 4, 0),
      new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
      new Knight(ChessEn.WhiteKnight, 7, 6, 0),
      new Rook(ChessEn.WhiteRook, 7, 7, 0)
    ),
  )
  override var score: Array[Int] = Array(1290, 1290)
  protected var whitePromButs: GridPane = _
  protected var blackPromButs: GridPane = _

  override def startGame(gamePane: StackPane): Unit = {
    ChessDrawer.setGamePane(gamePane)
    players(1 - turn).color = ChessEn.Black
    ChessDrawer.drawInit()
    players(ChessEn.Black).run(blackPromButs)
    players(ChessEn.White).run(whitePromButs)
    play()
  }

  override def play(): Unit = {
    if (players(turn).isInstanceOf[ChessAI] && !gameEnded)
      players(turn).Movement()
  }

  override def setPlayerMove(source: Node): Unit = {
    val player = players(turn).asInstanceOf[ChessPlayer]

    if ((player.state.newCol != player.state.oldCol || player.state.newRow != player.state.oldRow) &&
      ChessController.movementValidation(gameBoard, player.state).valid) {
      val removed = ChessController.createState(gameBoard, player.curPiece, player.state.newRow, player.state.newCol)

      if (!ChessController.checkMate(gameBoard, player.curPiece.color)) {
        ChessController.restoreState(gameBoard, player.curPiece, removed, player.state)
        executeMove(source, player.state)

        if (!player.promotion)
          update()
      } else
        ChessController.restoreState(gameBoard, player.curPiece, removed, player.state)
    }
  }

  override def update(): Unit = {
    turn = 1 - turn

    if (ChessController.checkEndGame(gameBoard, turn)) {
      gameEnded = true
      if (ChessController.checkMate(gameBoard, turn))
        ChessDrawer.drawEnd(1 - turn)
      else
        ChessDrawer.drawEnd(-1)
      return
    }

    if (ChessController.checkMate(gameBoard, turn))
      ChessController.findKing(gameBoard, turn).checked = true
    else
      ChessController.findKing(gameBoard, turn).checked = false

    play()
  }

  /**
   * Given a valid move for the current game, it apply the move for the game board and change the image place for the given piece.
   *
   * @param source the image of the piece associated with the current move.
   * @param s      the state of the piece which holds where it was and where it moved to.
   */
  def executeMove(source: Node, s: State): Unit = {
    ChessDrawer.highlightSquares(s)

    var curPiece = gameBoard(s.oldRow)(s.oldCol).asInstanceOf[ChessPiece]
    if (gameBoard(s.newRow)(s.newCol) != null) {
      score(1 - turn) -= gameBoard(s.newRow)(s.newCol).asInstanceOf[ChessPiece].rank
      ChessDrawer.gameBoard.getChildren.remove(gameBoard(s.newRow)(s.newCol).image)
    }

    if (curPiece.wantCastle(s.oldCol, s.newCol)) {
      val newRookCol = ChessController.kingCastling(gameBoard, s)

      ChessDrawer.movementDraw(gameBoard(s.newRow)(newRookCol).image,
        new State(0, 0, s.newRow, newRookCol, -1),
        gameBoard(s.newRow)(newRookCol).image)
    }

    gameBoard(s.oldRow)(s.oldCol) = null
    gameBoard(s.newRow)(s.newCol) = curPiece

    players(turn) match {
      case player: ChessPlayer =>
        ChessDrawer.movementDraw(source, new State(0, 0, s.newRow, s.newCol, -1), source)

        curPiece.firstMove = false
        curPiece.curCol = s.newCol
        curPiece.curRow = s.newRow

        if (curPiece.wantPromote()) {
          player.promotion = true
          player.src = source
          player.promButs.setVisible(true)
        }

      case _: ChessAI =>
        curPiece.firstMove = false
        curPiece.curCol = s.newCol
        curPiece.curRow = s.newRow

        if (curPiece.wantPromote()) {
          gameBoard(s.newRow)(s.newCol) =
            new Queen(if (turn == ChessEn.Black) ChessEn.BlackQueen else ChessEn.WhiteQueen, s.newRow, s.newCol, turn)
          ChessDrawer.gameBoard.getChildren.remove(curPiece.image)
          curPiece = gameBoard(s.newRow)(s.newCol).asInstanceOf[ChessPiece]
        }

        ChessDrawer.movementDraw(curPiece.image, new State(0, 0, s.newRow, s.newCol, -1), curPiece.image)
      case _ =>
    }
  }

  /**
   * Take a reference for a given promotion buttons for the white and black king.
   *
   * @param buts1 the buttons for the white king.
   * @param buts2 the buttons for the black king.
   */
  def setPromButs(buts1: GridPane, buts2: GridPane): Unit = {
    whitePromButs = buts1
    blackPromButs = buts2
  }
}
