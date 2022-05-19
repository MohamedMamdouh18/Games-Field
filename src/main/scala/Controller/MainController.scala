package Controller

import scalafx.event.ActionEvent
import javafx.scene.control.Button
import scalafxml.core.macros.sfxml

@sfxml
class MainController(private val but: Button) {

  def XOStart(event: ActionEvent): Unit = {
    but.setOnMouseClicked((event) => {
      println("XOStart")
    })
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
