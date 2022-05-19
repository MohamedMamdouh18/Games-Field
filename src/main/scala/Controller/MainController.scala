package Controller

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class MainController() {

  def XOStart(event: ActionEvent): Unit = {
    println("XO")
  }

  def ChessStart(event: ActionEvent): Unit = {
    println("Chess")
  }

  def Connect4Start(event: ActionEvent): Unit = {
    println("Connect-4")
  }

  def CheckersStart(event: ActionEvent): Unit = {
    println("Checkers")
  }

}
