package Controller

import javafx.scene.control.Button
import javafx.scene.layout.{AnchorPane, StackPane}
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class MainController(var gamePane: StackPane,
                     val menuPane: AnchorPane,
                     val returnButton: Button) {
  def XOStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("XO")
    val xoController = new XOController
    xoController.startGame(gamePane)
  }

  def ChessStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Chess")
    val chessController = new ChessController
    chessController.startGame(gamePane)
  }

  def Connect4Start(event: ActionEvent): Unit = {
    gameMode(true)
    println("Connect-4")
    val connectController = new ConnectController
    connectController.startGame(gamePane)
  }


  def CheckersStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Checkers")
    val checkersController = new CheckersController
    checkersController.startGame(gamePane)
  }

  def gameMode(boolean: Boolean): Unit = {
    gamePane.getChildren.clear()
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  def returnMenu(event: ActionEvent): Unit = {
    gameMode(false)
  }

}
