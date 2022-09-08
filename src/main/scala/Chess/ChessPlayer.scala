package Chess

import Base.Player.Player
import Base._
import Chess.Pieces._
import javafx.scene.Node
import javafx.scene.layout.GridPane

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
    var s, e: Int = 0
    if (color == ChessEn.Black) {
      s = 0
      e = 1
    } else {
      s = 6
      e = 7
    }

    promButs = buts
    gameDrawer = observer.gameDrawer.asInstanceOf[ChessDrawer]
    gameController = observer.gameController.asInstanceOf[ChessController]
    gameBoard = observer.gameBoard
    gameDrawer.setEvents(Movement, gameBoard, s, e)
    gameDrawer.preparePromotion(this)
  }

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      if (!promotion && !observer.gameEnded) {
        oldCol = GridPane.getColumnIndex(source)
        oldRow = GridPane.getRowIndex(source)
        x = e.getSceneX
        y = e.getSceneY
        curPiece = gameBoard(oldRow)(oldCol).asInstanceOf[ChessPiece]
      }
    })

    source.setOnMouseDragged(e => {
      if (curPiece.color == observer.turn && !promotion && !observer.gameEnded) {
        source.setTranslateX(e.getSceneX - x)
        source.setTranslateY(e.getSceneY - y)
      }
    })

    source.setOnMouseReleased(e => {
      if (curPiece.color == observer.turn && !promotion && !observer.gameEnded) {
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

            if (!promotion)
              Notify()
          }
        }
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

    gameBoard(oldRow)(oldCol) = null
    gameBoard(newRow)(newCol) = curPiece
    gameDrawer.movementDraw(source, new State(0, 0, newRow, newCol, 0), source)

    curPiece.firstMove = false
    curPiece.curCol = newCol
    curPiece.curRow = newRow
  }
}
