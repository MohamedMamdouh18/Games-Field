package GameEngines.Engines

import ChessPieces._
import GameEngines.Drawers.ChessDrawer
import GameEngines.GamesControllers.ChessController
import javafx.scene.Node

class ChessEngine extends GameEngine {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](8, 8)
  var oldCol, oldRow: Int = 0
  var x, y: Double = 0

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      gameDrawer.movementDraw(gameController.board, source, e, turn = false)
    })
    source.setOnMouseDragged(e => {
      gameDrawer.movementDraw(gameController.board, source, e, turn = false)
      //      source.setTranslateX(e.getSceneX - x)
      //      source.setTranslateY(e.getSceneY - y)
    })
    source.setOnMouseReleased(e => {
      gameDrawer.movementDraw(gameController.board, source, e, turn = false)
      //      if (movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
      //        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt)) {
      //        GridPane.setColumnIndex(source, Math.floor((e.getSceneX - 220) / 80).toInt)
      //        GridPane.setRowIndex(source, Math.floor((e.getSceneY - 100) / 80).toInt)
      //        source.setTranslateX(0)
      //        source.setTranslateY(0)
      //
      //      } else {
      //        GridPane.setRowIndex(source, oldRow)
      //        GridPane.setColumnIndex(source, oldCol)
      //        source.setTranslateX(0)
      //        source.setTranslateY(0)
      //      }
    })
  }
}

object ChessEngine {
  var board: Array[Array[Piece]] = Array(
    Array(new Castle("bcas", 0, 0, 1),
      new Knight("bhor", 0, 1, 1),
      new Bishop("bele", 0, 2, 1),
      new Queen("bque", 0, 3, 1),
      new King("bkin", 0, 4, 1),
      new Bishop("bele", 0, 5, 1),
      new Knight("bhor", 0, 6, 1),
      new Castle("bcas", 0, 7, 1)),

    Array(new Soldier("bsol", 1, 0, 1),
      new Soldier("bsol", 1, 1, 1),
      new Soldier("bsol", 1, 2, 1),
      new Soldier("bsol", 1, 3, 1),
      new Soldier("bsol", 1, 4, 1),
      new Soldier("bsol", 1, 5, 1),
      new Soldier("bsol", 1, 6, 1),
      new Soldier("bsol", 1, 7, 1)),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(new Soldier("wsol", 6, 0, 0),
      new Soldier("wsol", 6, 1, 0),
      new Soldier("wsol", 6, 2, 0),
      new Soldier("wsol", 6, 3, 0),
      new Soldier("wsol", 6, 4, 0),
      new Soldier("wsol", 6, 5, 0),
      new Soldier("wsol", 6, 6, 0),
      new Soldier("wsol", 6, 7, 0)),

    Array(new Castle("wcas", 7, 0, 1),
      new Knight("whor", 7, 1, 0),
      new Bishop("wele", 7, 2, 0),
      new Queen("wque", 7, 3, 0),
      new King("wkin", 7, 4, 0),
      new Bishop("wele", 7, 5, 0),
      new Knight("whor", 7, 6, 0),
      new Castle("wcas", 7, 7, 0)),
  )
}
