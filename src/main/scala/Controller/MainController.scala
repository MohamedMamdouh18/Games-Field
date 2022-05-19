package Controller

import javafx.scene.control.Button
import javafx.scene.layout.{AnchorPane, StackPane}
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class MainController(val gamePane: StackPane,
                     val menuPane: AnchorPane,
                     val returnButton: Button) {

  def XOStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("XO")
  }

  def ChessStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Chess")
    val chessController = new ChessController
    gamePane.setVisible(true)
    chessController.startGame(gamePane)
  }

  def Connect4Start(event: ActionEvent): Unit = {
    gameMode(true)
    println("Connect-4")
  }

  def gameMode(boolean: Boolean): Unit = {
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
  }

  def CheckersStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Checkers")
  }

  def returnMenu(event: ActionEvent): Unit = {
    gameMode(false)
  }

}
