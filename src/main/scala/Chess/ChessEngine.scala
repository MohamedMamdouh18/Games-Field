package Chess

import Base.{GameEngine, Piece, State}
import Chess.Pieces._
import javafx.scene.Node
import javafx.scene.layout.{GridPane, HBox, StackPane}

class ChessEngine(promButs: HBox) extends GameEngine {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[Piece]] = Array(
    Array(
      new Castle(ChessPieceEn.BlackCastle, 0, 0, 1),
      new Knight(ChessPieceEn.BlackKnight, 0, 1, 1),
      new Bishop(ChessPieceEn.BlackBishop, 0, 2, 1),
      new Queen(ChessPieceEn.BlackQueen, 0, 3, 1),
      new King(ChessPieceEn.BlackKing, 0, 4, 1),
      new Bishop(ChessPieceEn.BlackBishop, 0, 5, 1),
      new Knight(ChessPieceEn.BlackKnight, 0, 6, 1),
      new Castle(ChessPieceEn.BlackCastle, 0, 7, 1)),

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
      new Castle(ChessPieceEn.WhiteCastle, 7, 0, 0),
      new Knight(ChessPieceEn.WhiteKnight, 7, 1, 0),
      new Bishop(ChessPieceEn.WhiteBishop, 7, 2, 0),
      new Queen(ChessPieceEn.WhiteQueen, 7, 3, 0),
      new King(ChessPieceEn.WhiteKing, 7, 4, 0),
      new Bishop(ChessPieceEn.WhiteBishop, 7, 5, 0),
      new Knight(ChessPieceEn.WhiteKnight, 7, 6, 0),
      new Castle(ChessPieceEn.WhiteCastle, 7, 7, 0)),
  )
  var promotionMap: Array[Map[Int, ChessPiece]] = Array(
      Map(
        0 -> new Queen(ChessPieceEn.BlackQueen, 0, 0, 1),
        1 -> new Knight(ChessPieceEn.BlackKnight, 0, 0, 1),
        2 -> new Bishop(ChessPieceEn.BlackBishop, 0, 0, 1),
        3 -> new Castle(ChessPieceEn.BlackCastle, 0, 0, 1)
      ),
      Map(
        0 -> new Queen(ChessPieceEn.WhiteQueen, 0, 0, 0),
        1 -> new Knight(ChessPieceEn.WhiteKnight, 0, 0, 0),
        2 -> new Bishop(ChessPieceEn.WhiteBishop, 0, 0, 0),
        3 -> new Castle(ChessPieceEn.WhiteCastle, 0, 0, 0)
      ),
  )
  var oldCol, oldRow: Int = 0
  var newCol, newRow: Int = 0
  var curPiece: ChessPiece = _
  var x, y: Double = 0
  var src: Node = _
  var promotion: Boolean = false

  override def startGame(gamePane: StackPane): Unit = {
    gameDrawer.setGamePane(gamePane)
    gameDrawer.setEvent(Movement)
    gameDrawer.drawPiece(gameBoard)
    preparePromotion()
  }

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      if (!promotion) {
        oldCol = GridPane.getColumnIndex(source)
        oldRow = GridPane.getRowIndex(source)
        x = e.getSceneX
        y = e.getSceneY
        curPiece = gameBoard(oldRow)(oldCol).asInstanceOf[ChessPiece]
      }
    })

    source.setOnMouseDragged(e => {
      if (curPiece.color == turn && !promotion) {
        source.setTranslateX(e.getSceneX - x)
        source.setTranslateY(e.getSceneY - y)
      }
    })

    source.setOnMouseReleased(e => {
      if (curPiece.color == turn && !promotion) {
        newRow = Math.floor((e.getSceneY - 100) / 80).toInt
        newCol = Math.floor((e.getSceneX - 220) / 80).toInt

        source.setTranslateX(0)
        source.setTranslateY(0)
        val s: State = new State(oldRow, oldCol, newRow, newCol, turn)
        if ((newCol != oldCol || newRow != oldRow) && gameController.movementValidation(gameBoard, s).valid) {
          ReleaseLogic(source)
          turn = 1 - turn
        }
      }
    })
  }

  def preparePromotion(): Unit = {
    val buts = promButs.getChildren
    buts.forEach(_.setOnMousePressed(e => {
      val indx: Int = (e.getSceneX / 100).toInt

      val ps: ChessPiece = promotionMap(turn)(indx)
      ps.curCol = newCol
      ps.curRow = newRow

      Movement(ps.image)
      gameBoard(newRow)(newCol) = ps
      gameDrawer.gameBoard.getChildren.remove(src)
      gameDrawer.gameBoard.add(ps.image, newCol, newRow)

      promButs.setVisible(false)

      promotion = false
    }))
  }

  def ReleaseLogic(source: Node): Unit = {
    if (gameBoard(newRow)(newCol) != null) {
      gameDrawer.gameBoard.getChildren.remove(gameBoard(newRow)(newCol).image)
      gameBoard(newRow)(newCol) = null
    }

    if (curPiece.wantPromote()) {
      promotion = true
      src = source
      promButs.setVisible(true)
    }

    gameBoard(oldRow)(oldCol) = null
    gameBoard(newRow)(newCol) = curPiece
    gameDrawer.gameBoard.getChildren.remove(source)
    gameDrawer.gameBoard.add(source, newCol, newRow)

    curPiece.firstMove = false
    curPiece.curCol = newCol
    curPiece.curRow = newRow
  }
}
