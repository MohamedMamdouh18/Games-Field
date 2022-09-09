import Base.Player.{ConcretePlayer, Player}
import Chess.{ChessAI, ChessEngine, ChessPlayer}
import Connect4.{Connect4AI, Connect4Engine}
import XO.{XOAI, XOEngine}
import javafx.collections.FXCollections
import javafx.scene.control.{Button, ComboBox}
import javafx.scene.layout.{AnchorPane, GridPane, StackPane}
import javafx.util.Pair
import scalafxml.core.macros.sfxml


@sfxml
class MainController(var gamePane: StackPane, val menuPane: AnchorPane,
                     val returnButton: Button, gameMode: ComboBox[String],
                     val whitePromotionPieces: GridPane, val blackPromotionPieces: GridPane) {
  gameMode.getItems.addAll(FXCollections.observableArrayList("PvP", "PvA", "AvP", "AvA"))
  var gM: String = _
  val gameModeMap: Map[Pair[String, String], Array[Player]] = Map(
    new Pair[String, String]("PvA", "Chess") -> Array[Player](new ChessPlayer, new ChessAI),
    new Pair[String, String]("PvP", "Chess") -> Array[Player](new ChessPlayer, new ChessPlayer),
    new Pair[String, String]("AvA", "Chess") -> Array[Player](new ChessAI, new ChessAI),
    new Pair[String, String]("AvP", "Chess") -> Array[Player](new ChessAI, new ChessPlayer),

    new Pair[String, String]("PvA", "Connect4") -> Array[Player](new ConcretePlayer, new Connect4AI),
    new Pair[String, String]("PvP", "Connect4") -> Array[Player](new ConcretePlayer, new ConcretePlayer),
    new Pair[String, String]("AvA", "Connect4") -> Array[Player](new Connect4AI, new Connect4AI),
    new Pair[String, String]("AvP", "Connect4") -> Array[Player](new Connect4AI, new ConcretePlayer),

    new Pair[String, String]("PvA", "XO") -> Array[Player](new ConcretePlayer, new XOAI),
    new Pair[String, String]("PvP", "XO") -> Array[Player](new ConcretePlayer, new ConcretePlayer),
    new Pair[String, String]("AvA", "XO") -> Array[Player](new XOAI, new XOAI),
    new Pair[String, String]("AvP", "XO") -> Array[Player](new XOAI, new ConcretePlayer),
  )

  def XOStart(): Unit = {
    init()
    val players = gameModeMap(new Pair[String, String](gM, "XO"))
    val gameEngine = new XOEngine(players, gameMode.getValue)
    players(0).setObserver(gameEngine)
    players(1).setObserver(gameEngine)
    gameEngine.startGame(gamePane)
  }

  def ChessStart(): Unit = {
    init()
    val players = gameModeMap(new Pair[String, String](gM, "Chess"))
    val gameEngine = new ChessEngine(players, gameMode.getValue)
    players(0).setObserver(gameEngine)
    players(1).setObserver(gameEngine)
    gameEngine.setPromButs(whitePromotionPieces, blackPromotionPieces)
    gameEngine.startGame(gamePane)
  }

  def Connect4Start(): Unit = {
    init()
    val players = gameModeMap(new Pair[String, String](gM, "Connect4"))
    val gameEngine = new Connect4Engine(players, gameMode.getValue)
    players(0).setObserver(gameEngine)
    players(1).setObserver(gameEngine)
    gameEngine.startGame(gamePane)
  }

  def returnMenu(): Unit = {
    gameMode(false)
  }

  def gameMode(boolean: Boolean): Unit = {
    whitePromotionPieces.setVisible(false)
    blackPromotionPieces.setVisible(false)
    gamePane.getChildren.clear()
    gameMode.setVisible(!boolean)
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  def init(): Unit = {
    gameMode(true)
    gM = if (gameMode.getValue == null) "PvP" else gameMode.getValue
  }

}
