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

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
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
          }
        }
        if (!change_player) {
          if (PlayerRole == 1) {
            PlayerRole = 2
          }
          else {
            PlayerRole = 1
          }
        }

        true
      }
      else
        false
    }
    else
      false
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
    false
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
    false
  }

  def move(to_y: Int, to_x: Int, fromY: Int, fromX: Int): Unit = {
    var Source: Circle = new Circle()
    val childrens = board.getChildren
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
          gameBoard(fromY - 1)(fromX + 1) = "."
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
      eaten = false
    }
    if (promotion) {
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
