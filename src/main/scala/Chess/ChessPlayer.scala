package Chess

import Base.Player.Player
import Base._
import Chess.Pieces._
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class ChessPlayer extends Player {
  override var observer: GameEngine = _
  var gameController: ChessController = _
  var gameBoard: Array[Array[Piece]] = _
  var gameDrawer: ChessDrawer = _
  var promButs: GridPane = _
  var oldCol, oldRow: Int = 0
  var newCol, newRow: Int = 0
  var curPiece: ChessPiece = _
  var x, y: Double = 0
  var src: Node = _
  var promotion: Boolean = false

  override def run(buts: GridPane): Unit = {
    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard
    promButs = buts

    val p: Pair[Int, Int] = gameController.getPlayerPieces(color)

    gameDrawer.setEvents(Movement, gameBoard, p.getKey, p.getValue)
    gameDrawer.preparePromotion(this)
  }

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      if (!promotion && !observer.gameEnded && observer.turn == color) {
        oldCol = GridPane.getColumnIndex(source)
        oldRow = GridPane.getRowIndex(source)
        x = e.getSceneX
        y = e.getSceneY
        curPiece = gameBoard(oldRow)(oldCol).asInstanceOf[ChessPiece]
        gameDrawer.showAvailableMovements(this)
      }
    })

    source.setOnMouseDragged(e => {
      if (curPiece.color == observer.turn && !promotion && !observer.gameEnded) {
        source.setTranslateX(e.getSceneX - x)
        source.setTranslateY(e.getSceneY - y)
      }
    })

    source.setOnMouseReleased(e => {
      if (curPiece != null && curPiece.color == observer.turn && !promotion && !observer.gameEnded) {
        newRow = Math.floor((e.getSceneY - 100) / 80).toInt
        newCol = Math.floor((e.getSceneX - 220) / 80).toInt

        source.setTranslateX(0)
        source.setTranslateY(0)
        val s: State = new State(oldRow, oldCol, newRow, newCol, color)

        if ((newCol != oldCol || newRow != oldRow) && gameController.movementValidation(gameBoard, s).valid) {
          val removed = gameController.createState(gameBoard, curPiece, newRow, newCol)

          if (!gameController.checkMate(gameBoard, color)) {
            gameController.restoreState(gameBoard, curPiece, removed, oldRow, oldCol, newRow, newCol)
            ReleaseLogic(source)

            if (!promotion)
              Notify()
          } else
            gameController.restoreState(gameBoard, curPiece, removed, oldRow, oldCol, newRow, newCol)
        }
        gameDrawer.hideAvailableMovements()
      }
    })
  }

  private def ReleaseLogic(source: Node): Unit = {
    if (gameBoard(newRow)(newCol) != null) {
      observer.score(1 - color) -= gameBoard(newRow)(newCol).asInstanceOf[ChessPiece].rank
      gameDrawer.gameBoard.getChildren.remove(gameBoard(newRow)(newCol).image)
    }

    if (curPiece.wantPromote()) {
      promotion = true
      src = source
      promButs.setVisible(true)
    }

    if (curPiece.castled) {
      var oldRookCol, newRookCol: Int = -1
      if (newCol > oldCol) {
        newRookCol = 5
        oldRookCol = 7
      } else {
        newRookCol = 3
        oldRookCol = 0
      }

      gameBoard(newRow)(newRookCol) = gameBoard(newRow)(oldRookCol)
      gameBoard(newRow)(oldRookCol).curCol = newRookCol
      gameBoard(newRow)(oldRookCol) = null
      gameDrawer.movementDraw(gameBoard(newRow)(newRookCol).image,
        new State(0, 0, newRow, newRookCol, -1), gameBoard(newRow)(newRookCol).image)
      curPiece.castled = false
    }

    gameBoard(oldRow)(oldCol) = null
    gameBoard(newRow)(newCol) = curPiece
    gameDrawer.movementDraw(source, new State(0, 0, newRow, newCol, -1), source)

    curPiece.firstMove = false
    curPiece.curCol = newCol
    curPiece.curRow = newRow
  }
}
