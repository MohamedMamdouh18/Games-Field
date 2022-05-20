package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import scala.util.control.Breaks.{break, breakable}

class Connect4Controller extends Controller {

  var gameBoard: Array[Array[String]] = Array.ofDim[String](6, 7)
  var turn = "1"

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    checking(oldCol)
    true
  }
  override def Movement(source: Node): Unit = {
    source.setOnMouseClicked(e => {
      val circle = source.asInstanceOf[Circle]
      if (gameBoard(5-GridPane.getRowIndex(source))(GridPane.getColumnIndex(source))==null){
        movementValidation(GridPane.getColumnIndex(source),0,0,0)
        changeTurns()

      }
    }
    )


  }

  def checking(col: Int): Unit = {
    var i = 0
    var x = true
    while (x && i < 6) {
      if (gameBoard(i)(col) ==null)
        x = false
      else
        i += 1
    }
    if (i == 6) {
      println("this colomn is full")
      changeTurns()
      return
    }
    putting(5-i, col, turn)
    println(i)

  }

  def putting(row: Int, col: Int, color: String): Unit = {
    breakable {
      board.getChildren.forEach((node: Node) => {
        if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == row) && (GridPane.getColumnIndex(node) == col)) {
          val x=node.asInstanceOf[Circle]
          if (color=="1") x.setFill(Color.rgb(164, 43, 57))
          else x.setFill(Color.rgb(255, 221, 99))
          break
        }
      })
    }
    gameBoard(5-row)(col) = color
  }

  def changeTurns(): Unit = {
    if (turn == "1") turn = "2" else turn = "1"
  }

  def draw(): Unit = {
    for (i <- 5 to 0 by -1) {
      for (j <- 0 to 6) {
        print(" " + gameBoard(i)(j))
      }
      println()
    }
  }

}
