import Base.Player
import Chess.{ChessAI, ChessEngine, ChessPlayer}
import Connect4.{Connect4AI, Connect4Engine, Connect4Player}
import XO.{XOAI, XOEngine, XOPlayer}
import javafx.collections.FXCollections
import javafx.scene.control.{Button, ComboBox}
import javafx.scene.layout.{AnchorPane, GridPane, StackPane}
import javafx.util.Pair
import scalafx.scene.image.ImageView
import scalafxml.core.macros.sfxml


@sfxml
class MainController(var gamePane: StackPane, val menuPane: AnchorPane,
                     val returnButton: Button, val returnButtonImg: ImageView,
                     gameMode: ComboBox[String], difficulty: ComboBox[String],
                     val whitePromotionPieces: GridPane, val blackPromotionPieces: GridPane) {
  gameMode.getItems.addAll(FXCollections.observableArrayList("PvP", "PvA", "AvP"))
  difficulty.getItems.addAll(FXCollections.observableArrayList("Easy", "Normal", "Hard"))
  var gM: String = _
  var diff: String = _
  val gameModeMap: Map[Pair[String, String], Array[Player]] = Map(
    new Pair[String, String]("PvA", "Chess") -> Array[Player](new ChessPlayer, new ChessAI),
    new Pair[String, String]("PvP", "Chess") -> Array[Player](new ChessPlayer, new ChessPlayer),
    new Pair[String, String]("AvP", "Chess") -> Array[Player](new ChessAI, new ChessPlayer),

    new Pair[String, String]("PvA", "Connect4") -> Array[Player](new Connect4Player, new Connect4AI),
    new Pair[String, String]("PvP", "Connect4") -> Array[Player](new Connect4Player, new Connect4Player),
    new Pair[String, String]("AvP", "Connect4") -> Array[Player](new Connect4AI, new Connect4Player),

    new Pair[String, String]("PvA", "XO") -> Array[Player](new XOPlayer, new XOAI),
    new Pair[String, String]("PvP", "XO") -> Array[Player](new XOPlayer, new XOPlayer),
    new Pair[String, String]("AvP", "XO") -> Array[Player](new XOAI, new XOPlayer),
  )

  val difficultyMap: Map[String, Int] = Map(
    "Easy" -> 1,
    "Normal" -> 3,
    "Hard" -> 5,
  )

  /**
   * Starts XO Game when the button is pressed.
   */
  def XOStart(): Unit = {
    init()
    val players = gameModeMap(new Pair[String, String](gM, "XO"))
    val gameEngine = new XOEngine(players, gameMode.getValue)
    players(0).setObserver(gameEngine)
    players(1).setObserver(gameEngine)
    gameEngine.startGame(gamePane)
  }

  /**
   * Starts Chess Game when the button is pressed.
   */
  def ChessStart(): Unit = {
    init()
    val players = gameModeMap(new Pair[String, String](gM, "Chess"))
    val depth = difficultyMap(diff)
    players(0).setDepth(depth)
    players(1).setDepth(depth)
    val gameEngine = new ChessEngine(players, gameMode.getValue)
    players(0).setObserver(gameEngine)
    players(1).setObserver(gameEngine)
    gameEngine.setPromButs(whitePromotionPieces, blackPromotionPieces)
    gameEngine.startGame(gamePane)
  }

  /**
   * Start Connect-4 Game when the button is pressed.
   */
  def Connect4Start(): Unit = {
    init()
    val players = gameModeMap(new Pair[String, String](gM, "Connect4"))
    val depth = difficultyMap(diff)
    players(0).setDepth(depth)
    players(1).setDepth(depth)
    val gameEngine = new Connect4Engine(players, gameMode.getValue)
    players(0).setObserver(gameEngine)
    players(1).setObserver(gameEngine)
    gameEngine.startGame(gamePane)
  }

  /**
   * Returns to main menu when the button is pressed.
   */
  def returnMenu(): Unit = {
    gameMode(false)
  }

  /**
   * Sets some boxes to be visible and others invisible.
   * @param boolean indicate which to turn on.
   */
  def gameMode(boolean: Boolean): Unit = {
    whitePromotionPieces.setVisible(false)
    blackPromotionPieces.setVisible(false)
    gamePane.getChildren.clear()
    gameMode.setVisible(!boolean)
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    returnButtonImg.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  /**
   * Initialize the game.
   */
  def init(): Unit = {
    gameMode(true)
    gM = if (gameMode.getValue == null) "PvP" else gameMode.getValue
    diff = if (difficulty.getValue == null) "Normal" else difficulty.getValue
  }

}
