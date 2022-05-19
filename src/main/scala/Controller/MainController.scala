package Controller

import GameEngines.Drawers.{CheckersDrawer, ChessDrawer, Connect4Drawer, XODrawer}
import GameEngines.Engines.{CheckersEngine, ChessEngine, Connect4Engine, XOEngine}
import GameEngines.GamesControllers.{CheckersController, ChessController, Connect4Controller, XOController}
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
    val gameEngine = new XOEngine(new XODrawer, new XOController)
    gameEngine.startGame(gamePane)
  }

  def gameMode(boolean: Boolean): Unit = {
    gamePane.getChildren.clear()
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  def ChessStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Chess")
    val gameEngine = new ChessEngine(new ChessDrawer, new ChessController)
    gameEngine.startGame(gamePane)
  }

  def Connect4Start(event: ActionEvent): Unit = {
    gameMode(true)
    println("Connect-4")
    val gameEngine = new Connect4Engine(new Connect4Drawer, new Connect4Controller)
    gameEngine.startGame(gamePane)
  }

  def CheckersStart(event: ActionEvent): Unit = {
    gameMode(true)
    println("Checkers")
    val gameEngine = new CheckersEngine(new CheckersDrawer, new CheckersController)
    gameEngine.startGame(gamePane)
  }

  def returnMenu(event: ActionEvent): Unit = {
    gameMode(false)
  }

}
