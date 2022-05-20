package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane

class Connect4Controller extends Controller {

  var gameBoard: Array[Array[String]] = Array.ofDim[String](6, 7)
  var turn = 1

  override def Movement(source: Node): Unit = {
    checking(GridPane.getColumnIndex(source), turn)
    changeTurns()
    draw()

  }

  def checking(col: Int, color: Int): Unit = {
    var i = 0
    var x = true
    while (x && i < 6) {
      if (gameBoard(i)(col) == 0)
        x = false
      else
        i += 1
    }
    if (i == 6) {
      println("this colomn is full")
      changeTurns()
      return
    }
    putting(i, col, color)
    println(i)

  }

  def putting(row: Int, col: Int, color: Int): Unit = {
    //gameBoard(row)(col) = color
  }

  def changeTurns(): Unit = {
    if (turn == 1) turn = 2 else turn = 1
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
