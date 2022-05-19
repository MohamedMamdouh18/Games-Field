package Controller

import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent

import javafx.scene.layout.AnchorPane
import javafx.scene.control.Button

@sfxml
class MainController( val menuPane : AnchorPane,
                       val returnButton : Button) {

  def XOStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("XO")
  }

  def ChessStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Chess")
  }

  def Connect4Start(event: ActionEvent): Unit = {
    gameMode(true)
    println("Connect-4")
  }

  def CheckersStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Checkers")
  }

  def returnMenu(event: ActionEvent): Unit = {
    gameMode(false)
  }

  def gameMode(boolean: Boolean) : Unit = {
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
  }

}
