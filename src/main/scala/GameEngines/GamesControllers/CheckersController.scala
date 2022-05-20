package GameEngines.GamesControllers

import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle

import scala.util.control.Breaks.{break, breakable}

class CheckersController extends Controller {
  override var gameBoard: Array[Array[String]] = _
  var x_8: Int = 0
  var y_8: Int = 0
  var eat = false
  var Player_turn = 1
  var fromPiece = "x"
  var eaten = false
  var change_player = false
  var promoted: Map[Int, Int] = Map()
  var promotion = false
  var swapTurn = false

  override def Movement(source: Node): Unit = {
    source.setOnMousePressed(e => {
      oldCol = GridPane.getColumnIndex(source)
      oldRow = GridPane.getRowIndex(source)
      x = e.getSceneX - source.getTranslateX
      y = e.getSceneY - source.getTranslateY
    })
    source.setOnMouseDragged(e => {
      source.setTranslateX(e.getSceneX - x)
      source.setTranslateY(e.getSceneY - y)
    })
    source.setOnMouseReleased(e => {
      // val source = e.getTarget.asInstanceOf[Node]
      if (movementValidation(GridPane.getColumnIndex(source), GridPane.getRowIndex(source),
        Math.floor((e.getSceneX - 220) / 80).toInt, Math.floor((e.getSceneY - 100) / 80).toInt)) {
        GridPane.setColumnIndex(source, Math.floor((e.getSceneX - 220) / 80).toInt)
        GridPane.setRowIndex(source, Math.floor((e.getSceneY - 100) / 80).toInt)
        source.setTranslateX(0)
        source.setTranslateY(0)

      } else {
        GridPane.setRowIndex(source, oldRow)
        GridPane.setColumnIndex(source, oldCol)
        source.setTranslateX(0)
        source.setTranslateY(0)
      }
    })
  }

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    println(gameBoard(newRow)(newCol))
    println(oldCol + " " + oldRow + " " + newCol + " " + newRow)
    val to_x = newCol
    val to_y = newRow + 1
    val fromX = oldCol
    val fromY = oldRow + 1
    Player_turn = PlayerRole
    if (validFrom(fromY, fromX)) {
      if (validto(to_y, to_x, fromY, fromX)) {
        move(to_y, to_x, fromY, fromX)
        for (i <- 0 to 8) {
          for (j <- 0 to gameBoard(i).length - 1) {
            print(" " + gameBoard(i)(j))
          }
          println()
        }
        if (!change_player) {
          if (PlayerRole == 1) {
            PlayerRole = 2
          }
          else {
            PlayerRole = 1
          }
        }

        return true
      }
      else {
        return false

      }
    }
    else {
      return false
    }

  }

  def validFrom(fromY: Int, fromX: Int): Boolean = {
    if (Player_turn == 1) {
      if (gameBoard(fromY)(fromX) == "x") {

        fromPiece = gameBoard(fromY)(fromX)
        return true
      }
    } else {
      if (gameBoard(fromY)(fromX) == "y") {
        fromPiece = gameBoard(fromY)(fromX)
        return true
      }
    }
    return false
  }

  def validto(to_y: Int, to_x: Int, fromY: Int, fromX: Int): Boolean = {
    if (Player_turn == 1) {
      if (gameBoard(to_y)(to_x) == "x") {
        return false
      }
    }
    else {
      if (gameBoard(to_y)(to_x) == "y") {
        return false
      }
    }
    if (Player_turn == 1) {
      if (promoted.contains(fromY)) {
        if (((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1) || (to_y == fromY + 1 && to_x == fromX + 1) || (to_y == fromY + 1 && to_x == fromX - 1)) && gameBoard(to_y)(to_x) != "x") {
          promotion = true
          return true
        }
        else if ((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2) || (to_y == fromY + 2 && to_x == fromX + 2) || (to_y == fromY + 2 && to_x == fromX - 2)) {
          if (gameBoard(fromY - 1)(fromX + 1) == "y" || gameBoard(fromY - 1)(fromX - 1) == "y") {
            eaten = true
            eat = true
            promotion = true
            return true
          }
          else {
            promotion = false
            return false
          }
        }
      }
      else {
        if (((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1)) && gameBoard(to_y)(to_x) != "y") {
          return true
        }
        else if ((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2)) {
          if (gameBoard(fromY - 1)(fromX + 1) == "y" || gameBoard(fromY - 1)(fromX - 1) == "y") {
            eaten = true
            eat = true
            return true
          }
          else {
            return false
          }
        }
      }
    }
    else {
      if (promoted.contains(fromY)) {
        if (((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1) || (to_y == fromY + 1 && to_x == fromX + 1) || (to_y == fromY + 1 && to_x == fromX - 1)) && gameBoard(to_y)(to_x) != "y") {
          promotion = true
          return true
        }
        else if ((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2) || (to_y == fromY + 2 && to_x == fromX + 2) || (to_y == fromY + 2 && to_x == fromX - 2)) {

          if (gameBoard(fromY - 1)(fromX + 1) == "x" || gameBoard(fromY - 1)(fromX - 1) == "x") {
            eaten = true
            eat = true
            promotion = true
            return true
          }
          else {
            promotion = false
            return false
          }
        }
      }
      else {
        if (((to_y == fromY + 1 && to_x == fromX + 1) || (to_y == fromY + 1 && to_x == fromX - 1)) && gameBoard(to_y)(to_x) != "x") {

          return true
        }
        else if ((to_y == fromY + 2 && to_x == fromX + 2) || (to_y == fromY + 2 && to_x == fromX - 2)) {
          if ((gameBoard(fromY + 1)(fromX + 1) == "x") || (gameBoard(fromY + 1)(fromX - 1) == "x")) {
            eaten = true
            eat = true
            return true
          }
          else {
            return false
          }
        }
      }
    }
    return false
  }

  def move(to_y: Int, to_x: Int, fromY: Int, fromX: Int) = {
    var Source: Circle = new Circle()
    val childrens = board.getChildren
    println(eaten)
    gameBoard(to_y)(to_x) = gameBoard(fromY)(fromX)
    if (Player_turn == 1) {
      if (to_y == 1) {
        promoted = promoted + (to_y -> to_x)
      }
    }
    else {
      if (to_y == 8) {
        promoted = promoted + (to_y -> to_x)
      }
    }
    if (eaten) {
      if (to_y < fromY && to_x < fromX) {
        if ((fromY + fromX) % 2 == 0) {
          gameBoard(fromY - 1)(fromX - 1) = "-"
          x_8 = fromX - 1
          y_8 = fromY - 2
          println("lol1")
          breakable {
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else {
          gameBoard(fromY - 1)(fromX - 1) = "."
          x_8 = fromX - 1
          y_8 = fromY - 2
          println("lol2")
          breakable {
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        if (to_x != 0 && to_y != 0) {
          if (gameBoard(to_y - 1)(to_x - 1) == "y" && (gameBoard(to_y - 2)(to_x - 2) == "." || gameBoard(to_y - 2)(to_x - 2) == "-") || gameBoard(to_y - 1)(to_x + 1) == "y" && (gameBoard(to_y - 2)(to_x + 2) == "." || gameBoard(to_y - 2)(to_x + 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
        else if (to_y != 0) {
          if (gameBoard(to_y - 1)(to_x + 1) == "y" && (gameBoard(to_y - 2)(to_x + 2) == "." || gameBoard(to_y - 2)(to_x + 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }

      }
      else if (to_y < fromY && to_x > fromX) {
        if ((fromY + fromX) % 2 == 0) {
          println("lol3")
          gameBoard(fromY - 1)(fromX + 1) = "-"
          x_8 = fromX + 1
          y_8 = fromY - 2
          breakable {
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else {
          println("lol4")
          println(gameBoard(4)(5))
          gameBoard(fromY - 1)(fromX + 1) = "."
          println(gameBoard(4)(5))
          println(fromY - 1)
          println(fromX + 1)
          x_8 = fromX + 1
          y_8 = fromY - 2
          breakable {
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        if (to_x != 0 && to_y != 0) {
          if (gameBoard(to_y - 1)(to_x - 1) == "y" && (gameBoard(to_y - 2)(to_x - 2) == "." || gameBoard(to_y - 2)(to_x - 2) == "-") || gameBoard(to_y - 1)(to_x + 1) == "y" && (gameBoard(to_y - 2)(to_x + 2) == "." || gameBoard(to_y - 2)(to_x + 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
        else if (to_y != 0) {
          if (gameBoard(to_y - 1)(to_x + 1) == "y" && (gameBoard(to_y - 2)(to_x + 2) == "." || gameBoard(to_y - 2)(to_x + 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
      }
      else if (to_y > fromY && to_x > fromX) {
        if ((fromY + fromX) % 2 == 0) {
          println("lol5")
          gameBoard(fromY + 1)(fromX + 1) = "-"

          x_8 = fromX + 1
          y_8 = fromY
          breakable {
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else {
          gameBoard(fromY + 1)(fromX + 1) = "."
          x_8 = fromX + 1
          y_8 = fromY
          breakable {
            println("lol6")
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }

        }
        if (to_x != 0) {
          if ((gameBoard(to_y + 1)(to_x + 1) == "y" && (gameBoard(to_y + 2)(to_x + 2) == "." || gameBoard(to_y + 2)(to_x + 2) == "-")) || gameBoard(to_y + 1)(to_x - 1) == "y" && (gameBoard(to_y + 2)(to_x - 2) == "." || gameBoard(to_y + 2)(to_x - 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
        else {
          if (gameBoard(to_y + 1)(to_x + 1) == "y" && (gameBoard(to_y + 2)(to_x + 2) == "." || gameBoard(to_y + 2)(to_x + 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
      }
      else {
        if ((fromY + fromX) % 2 == 0) {
          gameBoard(fromY + 1)(fromX - 1) = "-"

          x_8 = fromX - 1
          y_8 = fromY
          println("lol7")
          breakable {
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else {
          gameBoard(fromY + 1)(fromX - 1) = "."
          x_8 = fromX - 1
          y_8 = fromY
          println("lol8")
          breakable {
            childrens.forEach((node: Node) => {
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }

        }
        if (to_x != 0) {
          if ((gameBoard(to_y + 1)(to_x + 1) == "y" && (gameBoard(to_y + 2)(to_x + 2) == "." || gameBoard(to_y + 2)(to_x + 2) == "-")) || gameBoard(to_y + 1)(to_x - 1) == "y" && (gameBoard(to_y + 2)(to_x - 2) == "." || gameBoard(to_y + 2)(to_x - 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
        else {
          if (gameBoard(to_y + 1)(to_x + 1) == "y" && (gameBoard(to_y + 2)(to_x + 2) == "." || gameBoard(to_y + 2)(to_x + 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
      }
      if (promotion) {
        if (to_x != 0 && to_y != 0) {
          if (gameBoard(to_y + 1)(to_x - 1) == "y" && (gameBoard(to_y + 2)(to_x - 2) == "." || gameBoard(to_y + 2)(to_x - 2) == "-") || gameBoard(to_y + 1)(to_x + 1) == "y" && (gameBoard(to_y + 2)(to_x + 2) == "." || gameBoard(to_y + 2)(to_x + 2) == "-") || gameBoard(to_y - 1)(to_x + 1) == "y" && (gameBoard(to_y - 2)(to_x + 2) == "." || gameBoard(to_y - 2)(to_x + 2) == "-") || gameBoard(to_y - 1)(to_x - 1) == "y" && (gameBoard(to_y - 2)(to_x - 2) == "." || gameBoard(to_y - 2)(to_x - 2) == "-")) {
            change_player = true
          }
          else {
            change_player = false
          }
        }
      }
      println("values")
      println(x_8)
      println(y_8)
      eaten = false
    }
    if (promotion == true) {
      promoted = promoted.removed(fromY)
      promoted = promoted + (to_y -> to_x)
      promotion = false
    }
    if ((fromY + fromX) % 2 == 0) {
      gameBoard(fromY)(fromX) = "-"
    }
    else {
      gameBoard(fromY)(fromX) = "."
    }

  }
}
