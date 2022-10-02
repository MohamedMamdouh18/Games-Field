package Chess

import Base._
import Chess.Pieces._
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.util.Pair

class ChessPlayer extends Player {
  val state: State = new State(-1, -1, -1, -1, color)
  override var observer: GameEngine = _
  var gameBoard: Array[Array[Piece]] = _
  var promButs: GridPane = _
  var curPiece: ChessPiece = _
  var src: Node = _
  var promotion: Boolean = false
  private var x, y: Double = 0

  override def run(buts: GridPane): Unit = {
    gameBoard = observer.gameBoard
    promButs = buts

    val p: Pair[Int, Int] = ChessController.getPlayerPieces(color)

    ChessDrawer.setEvents(Movement, gameBoard, p.getKey, p.getValue)
    ChessDrawer.preparePromotion(this)
  }

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      if (!promotion && !observer.gameEnded && observer.turn == color) {
        state.oldCol = GridPane.getColumnIndex(source)
        state.oldRow = GridPane.getRowIndex(source)
        x = e.getSceneX
        y = e.getSceneY
        curPiece = gameBoard(state.oldRow)(state.oldCol).asInstanceOf[ChessPiece]
        ChessDrawer.showAvailableMovements(this)
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
        ChessDrawer.hideAvailableMovements()
      }
    })
  }

  override def Notify(): Unit = {
    observer.setPlayerMove(src)
  }
}
