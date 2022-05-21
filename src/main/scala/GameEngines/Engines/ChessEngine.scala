package GameEngines.Engines

import ChessPieces._
import GameEngines.Drawers.ChessDrawer
import GameEngines.GamesControllers.ChessController
import javafx.scene.Node
import javafx.scene.layout.GridPane

class ChessEngine extends GameEngine {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](8, 8)
  var oldCol, oldRow: Int = 0
  var newCol, newRow: Int = 0
  var curPiece: Piece = null
  var x, y: Double = 0
  var turn: Int = 0

  override def Movement(source: Node): Unit = {

    source.setOnMousePressed(e => {
      oldCol = GridPane.getColumnIndex(source)
      oldRow = GridPane.getRowIndex(source)
      x = e.getSceneX //get column
      y = e.getSceneY //get row
      curPiece = ChessController.board(oldRow)(oldCol)
      //      println(x)
      //      println(y)
      //      println(curPiece)
    })
    source.setOnMouseDragged(e => {
      if (curPiece.color == turn) {
        source.setTranslateX(e.getSceneX - x)
        source.setTranslateY(e.getSceneY - y)
      }
    })
    source.setOnMouseReleased(e => {
      if (curPiece.color == turn) {
        newRow = Math.floor((e.getSceneY - 100) / 80).toInt
        newCol = Math.floor((e.getSceneX - 220) / 80).toInt
        println(newCol)
        println(newRow)
        source.setTranslateX(0)
        source.setTranslateY(0)
        if ((newCol != oldCol || newRow != oldRow) && curPiece.validateMove(newCol, newRow)) {
          if (ChessController.board(newRow)(newCol) != null) {
            gameController.board.getChildren.remove(ChessController.board(newRow)(newCol).image)
            ChessController.board(newRow)(newCol) = null
          }
          ChessController.board(oldRow)(oldCol) = null
          ChessController.board(newRow)(newCol) = curPiece
          gameController.board.getChildren.remove(source)
          gameController.board.add(source, newCol, newRow)

          curPiece.firstMove = false
          curPiece.curCol = newCol
          curPiece.curRow = newRow

          turn = 1 - turn

        } else {
          println("bad")
          //                GridPane.setRowIndex(source, oldRow)
          //                GridPane.setColumnIndex(source, oldCol)
        }
      }
    })
  }
}

