package Engines

import ChessPieces._
import Controllers.ChessController
import Drawers.ChessDrawer
import javafx.scene.Node
import javafx.scene.layout.{GridPane, HBox}

class ChessEngine(promButs: HBox) extends GameEngine {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](8, 8)
  var oldCol, oldRow: Int = 0
  var newCol, newRow: Int = 0
  var curPiece: Piece = null
  var x, y: Double = 0
  var turn: Int = 0
  var src : Node = null
  var promotion: Boolean = false

  preparePromotion()

  override def Movement(source: Node): Unit = {

    source.setOnMousePressed(e => {
      if (!promotion) {
        oldCol = GridPane.getColumnIndex(source)
        oldRow = GridPane.getRowIndex(source)
        x = e.getSceneX //get column
        y = e.getSceneY //get row
        curPiece = ChessController.board(oldRow)(oldCol)
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
        if ((newCol != oldCol || newRow != oldRow) && curPiece.validateMove(newCol, newRow)) {
          if (ChessController.board(newRow)(newCol) != null) {
            gameController.board.getChildren.remove(ChessController.board(newRow)(newCol).image)
            ChessController.board(newRow)(newCol) = null
          }

          if (curPiece.wantPromote()) {
            promotion = true
            src = source
            promButs.setVisible(true)
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
          println(curPiece.curRow)
          println(curPiece.curCol)
          println(oldRow)
          println(oldCol)
          //                GridPane.setRowIndex(source, oldRow)
          //                GridPane.setColumnIndex(source, oldCol)
        }
      }
    })
  }

  def preparePromotion(): Unit = {
    var buts = promButs.getChildren
    buts.forEach(_.setOnMousePressed(e => {
      var ps : Piece = null
      var indx : Int = (e.getSceneX/100).toInt
      var name : String = null

      if(indx == 0){
        if(turn == 0){
          name ="bque"
          ps = new Queen(name , newRow , newCol , 1 )
        }
        if(turn == 1) {
          name ="wque"
          ps = new Queen(name , newRow , newCol , 0 )
        }
      }else if(indx == 1){
        if(turn == 0) {
          name ="bhor"
          ps = new Knight(name , newRow , newCol , 1 )
        }
        if(turn == 1) {
          name ="whor"
          ps = new Knight(name , newRow , newCol , 0 )
        }
      }else if(indx == 2){
        if(turn == 0) {
          name ="bele"
          ps = new Bishop(name , newRow , newCol , 1 )
        }
        if(turn == 1) {
          name ="wele"
          ps = new Bishop(name , newRow , newCol , 0 )
        }
      }else{
        if(turn == 0) {
          name ="bcas"
          ps = new Castle(name , newRow , newCol , 1 )
        }
        if(turn == 1) {
          name ="wcas"
          ps = new Castle(name , newRow , newCol , 0 )
        }
      }
      Movement(ps.image)
      ChessController.board(newRow)(newCol) = ps
      gameController.board.getChildren.remove(src)
      gameController.board.add(ps.image, newCol, newRow)

      promButs.setVisible(false)

      promotion = false
    }))
  }
}

