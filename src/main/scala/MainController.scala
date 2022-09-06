import Chess.{ChessEngine, ChessPlayer}
import Connect4.Connect4Engine
import XO.XOEngine
import javafx.scene.control.Button
import javafx.scene.layout.{AnchorPane, HBox, StackPane}
import scalafxml.core.macros.sfxml


@sfxml
class MainController(var gamePane: StackPane,
                     val menuPane: AnchorPane,
                     val returnButton: Button,
                     val promButs: HBox) {
  def XOStart(): Unit = {
    gameMode(true)
    val gameEngine = new XOEngine(null, null, null)
    gameEngine.startGame(gamePane)
  }

  def ChessStart(): Unit = {
    gameMode(true)
    val player1, player2: ChessPlayer = new ChessPlayer
    val gameEngine = new ChessEngine(player1, player2, "PvP")
    player1.setObserver(gameEngine)
    player2.setObserver(gameEngine)
    gameEngine.setPromButs(promButs)
    gameEngine.startGame(gamePane)
  }


  def Connect4Start(): Unit = {
    gameMode(true)
    val gameEngine = new Connect4Engine(null, null, null)
    gameEngine.startGame(gamePane)
  }

  def gameMode(boolean: Boolean): Unit = {
    promButs.setVisible(false)
    gamePane.getChildren.clear()
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  def returnMenu(): Unit = {
    gameMode(false)
  }

}
