package Controller

import GameEngines.Engines.{CheckersEngine, ChessEngine, Connect4Engine, XOEngine}
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
    val gameEngine = new XOEngine
    gameEngine.startGame(gamePane)
  }

  def ChessStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Chess")
    val gameEngine = new ChessEngine
    gameEngine.startGame(gamePane)
  }

  def Connect4Start(event: ActionEvent): Unit = {
    gameMode(true)
    println("Connect-4")
    val gameEngine = new Connect4Engine
    gameEngine.startGame(gamePane)
  }

  def gameMode(boolean: Boolean): Unit = {
    gamePane.getChildren.clear()
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  def CheckersStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Checkers")
    val gameEngine = new CheckersEngine
    gameEngine.startGame(gamePane)
  }

  def returnMenu(event: ActionEvent): Unit = {
    gameMode(false)
  }

}
