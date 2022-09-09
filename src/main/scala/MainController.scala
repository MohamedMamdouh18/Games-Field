import Base.Player.{ConcreteAI, ConcretePlayer, Player}
import Chess.{AIChess, ChessEngine, ChessPlayer}
import Connect4.{Connect4AI, Connect4Engine}
import XO.XOEngine
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
    new Pair[String, String]("PvA", "Chess") -> Array[Player](new ChessPlayer, new AIChess),
    new Pair[String, String]("PvP", "Chess") -> Array[Player](new ChessPlayer, new ChessPlayer),
    new Pair[String, String]("AvA", "Chess") -> Array[Player](new AIChess, new AIChess),
    new Pair[String, String]("AvP", "Chess") -> Array[Player](new AIChess, new ChessPlayer),

    new Pair[String, String]("PvA", "Connect4") -> Array[Player](new ConcretePlayer, new Connect4AI),
    new Pair[String, String]("PvP", "Connect4") -> Array[Player](new ConcretePlayer, new ConcretePlayer),
    new Pair[String, String]("AvA", "Connect4") -> Array[Player](new Connect4AI, new Connect4AI),
    new Pair[String, String]("AvP", "Connect4") -> Array[Player](new Connect4AI, new ConcretePlayer),

    new Pair[String, String]("PvA", "Concrete") -> Array[Player](new ConcretePlayer, new ConcreteAI),
    new Pair[String, String]("PvP", "Concrete") -> Array[Player](new ConcretePlayer, new ConcretePlayer),
    new Pair[String, String]("AvA", "Concrete") -> Array[Player](new ConcreteAI, new ConcreteAI),
    new Pair[String, String]("AvP", "Concrete") -> Array[Player](new ConcreteAI, new ConcretePlayer),
  )

  def XOStart(): Unit = {
    init()
    val players = gameModeMap(new Pair[String, String](gM, "Concrete"))
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
