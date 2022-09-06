package Chess

import Base.{Drawer, GameEngine, Piece, Player, State}
import Chess.Pieces._
import javafx.scene.Node
import javafx.scene.layout.{GridPane, HBox}

class ChessPlayer extends Player {
  override val gameController = new ChessController
  override var gameBoard: Array[Array[Piece]] = _
  override var gameDrawer: Drawer = _
  override var observer: GameEngine = _
  override var turn: Array[Int] = _
  var promButs: HBox = _
  var promotionMap: Array[Map[Int, Promotion]] = Array(
    Map(
      0 -> ((r, c) => new Queen(ChessPieceEn.WhiteQueen, r, c, 0)),
      1 -> ((r, c) => new Knight(ChessPieceEn.WhiteKnight, r, c, 0)),
      2 -> ((r, c) => new Bishop(ChessPieceEn.WhiteBishop, r, c, 0)),
      3 -> ((r, c) => new Castle(ChessPieceEn.WhiteCastle, r, c, 0))
    ),
    Map(
      0 -> ((r, c) => new Queen(ChessPieceEn.BlackQueen, r, c, 1)),
      1 -> ((r, c) => new Knight(ChessPieceEn.BlackKnight, r, c, 1)),
      2 -> ((r, c) => new Bishop(ChessPieceEn.BlackBishop, r, c, 1)),
      3 -> ((r, c) => new Castle(ChessPieceEn.BlackCastle, r, c, 1))
    ),
  )
  var oldCol, oldRow: Int = 0
  var newCol, newRow: Int = 0
  var curPiece: ChessPiece = _
  var x, y: Double = 0
  var src: Node = _
  var gameEnded: Boolean = false
  var checkGameEnd: Boolean = false
  var promotion: Boolean = false

  override def run(board: Array[Array[Piece]], turn: Array[Int], drawer: Drawer, buts: HBox): Unit = {
    var s, e: Int = 0
    if (color == 1) {
      s = 0
      e = 1
    } else {
      s = 6
      e = 7
    }
    this.turn = turn
    promButs = buts
    gameDrawer = drawer.asInstanceOf[ChessDrawer]
    gameBoard = board
    gameDrawer.setEvents(Movement, gameBoard, s, e)
  }

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      if (!promotion && !gameEnded) {
        oldCol = GridPane.getColumnIndex(source)
        oldRow = GridPane.getRowIndex(source)
        x = e.getSceneX
        y = e.getSceneY
        curPiece = gameBoard(oldRow)(oldCol).asInstanceOf[ChessPiece]
      }
    })

    source.setOnMouseDragged(e => {
      if (curPiece.color == turn(0) && !promotion && !gameEnded) {
        source.setTranslateX(e.getSceneX - x)
        source.setTranslateY(e.getSceneY - y)
      }
    })

    source.setOnMouseReleased(e => {
      if (curPiece.color == turn(0) && !promotion && !gameEnded) {
        newRow = Math.floor((e.getSceneY - 100) / 80).toInt
        newCol = Math.floor((e.getSceneX - 220) / 80).toInt

        source.setTranslateX(0)
        source.setTranslateY(0)
        val s: State = new State(oldRow, oldCol, newRow, newCol, color)

        if ((newCol != oldCol || newRow != oldRow) && gameController.movementValidation(gameBoard, s).valid) {
          val newBoard = gameController.copyBoard(gameBoard)

          gameController.createState(newBoard, newBoard(oldRow)(oldCol), newRow, newCol)

          if (!gameController.checkMate(newBoard, color)) {
            ReleaseLogic(source)
            checkGameEnd = false
            if (!promotion)
              Notify()
          } else if (!checkGameEnd) {
            if (gameController.checkEndGame(gameController.copyBoard(gameBoard), color))
              gameEnded = true

            checkGameEnd = true
          }
        }
      }
    })
  }

  def ReleaseLogic(source: Node): Unit = {
    if (gameBoard(newRow)(newCol) != null)
      gameDrawer.gameBoard.getChildren.remove(gameBoard(newRow)(newCol).image)

    if (curPiece.wantPromote()) {
      promotion = true
      src = source
      promButs.setVisible(true)
    }

    gameBoard(oldRow)(oldCol) = null
    gameBoard(newRow)(newCol) = curPiece
    gameDrawer.movementDraw(source, new State(0, 0, newRow, newCol, 0), source)

    curPiece.firstMove = false
    curPiece.curCol = newCol
    curPiece.curRow = newRow
  }

  override def Notify(): Unit = {
    observer.update()
  }

  def preparePromotion(): Unit = {
    val buts = promButs.getChildren
    buts.forEach(_.setOnMousePressed(e => {
      val indx: Int = (e.getSceneX / 100).toInt
      val ps: ChessPiece = promotionMap(color)(indx).promote(newRow, newCol)
      Movement(ps.image)
      println(color, "in", turn(0), ps.name)
      gameBoard(newRow)(newCol) = ps
      gameDrawer.movementDraw(src, new State(0, 0, newRow, newCol, 0), ps.image)

      promButs.setVisible(false)
      promotion = false
      Notify()
    }))
  }

  def setObserver(gameEngine: GameEngine): Unit = {
    observer = observer.asInstanceOf[ChessEngine]
    observer = gameEngine.asInstanceOf[ChessEngine]
  }
}
