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
  val state: State = new State(-1, -1, -1, -1, color)
  var curPiece: ChessPiece = _
  private var x, y: Double = 0
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
        state.oldCol = GridPane.getColumnIndex(source)
        state.oldRow = GridPane.getRowIndex(source)
        x = e.getSceneX
        y = e.getSceneY
        curPiece = gameBoard(state.oldRow)(state.oldCol).asInstanceOf[ChessPiece]
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
        state.newRow = Math.floor((e.getSceneY - 65) / 80).toInt
        state.newCol = Math.floor((e.getSceneX - 220) / 80).toInt

        source.setTranslateX(0)
        source.setTranslateY(0)
        src = source

        Notify()
        gameDrawer.hideAvailableMovements()
      }
    })
  }

  override def Notify(): Unit = {
    observer.setPlayerMove(src)
  }
}
