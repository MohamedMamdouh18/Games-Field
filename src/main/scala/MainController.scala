import Base.{ConcretePlayer, Player}
import Chess.{ChessEngine, ChessPlayer}
import Connect4.Connect4Engine
import XO.XOEngine
import javafx.scene.control.Button
import javafx.scene.layout.{AnchorPane, GridPane, StackPane}
import scalafxml.core.macros.sfxml


@sfxml
class MainController(var gamePane: StackPane, val menuPane: AnchorPane, val returnButton: Button,
                     val whitePromotionPieces: GridPane, val blackPromotionPieces: GridPane) {
  def XOStart(): Unit = {
    gameMode(true)
    val player1, player2: Player = new ConcretePlayer
    val gameEngine = new XOEngine(player1, player2, "PvP")
    player1.setObserver(gameEngine)
    player2.setObserver(gameEngine)
    gameEngine.startGame(gamePane)
  }

  def ChessStart(): Unit = {
    gameMode(true)
    val player1, player2: ChessPlayer = new ChessPlayer
    val gameEngine = new ChessEngine(player1, player2, "PvP")
    player1.setObserver(gameEngine)
    player2.setObserver(gameEngine)
    gameEngine.setPromButs(whitePromotionPieces, blackPromotionPieces)
    gameEngine.startGame(gamePane)
  }

  def Connect4Start(): Unit = {
    gameMode(true)
    val player1, player2: Player = new ConcretePlayer
    val gameEngine = new Connect4Engine(player1, player2, "PvP")
    player1.setObserver(gameEngine)
    player2.setObserver(gameEngine)
    gameEngine.startGame(gamePane)
  }

  def gameMode(boolean: Boolean): Unit = {
    whitePromotionPieces.setVisible(false)
    blackPromotionPieces.setVisible(false)
    gamePane.getChildren.clear()
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  def returnMenu(): Unit = {
    gameMode(false)
  }

}
