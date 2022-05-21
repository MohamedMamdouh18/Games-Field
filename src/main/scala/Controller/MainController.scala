package Controller

import GameEngines.Engines.{CheckersEngine, ChessEngine, Connect4Engine, XOEngine}
import javafx.animation.{KeyFrame, Timeline}
import javafx.event.Event
import javafx.scene.control.Button
import javafx.scene.layout.{AnchorPane, StackPane, VBox}
import javafx.scene.media.{Media, MediaPlayer, MediaView}
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.util.Duration
import scalafxml.core.macros.sfxml

import java.io.File


@sfxml
class MainController(var gamePane: StackPane,
                     val menuPane: AnchorPane,
                     val returnButton: Button) {
  def XOStart(): Unit = {
    gameMode(true)
    val gameEngine = new XOEngine
    gameEngine.startGame(gamePane)
  }

  def ChessStart(): Unit = {
    gameMode(true)
    val gameEngine = new ChessEngine
    gameEngine.startGame(gamePane)
  }

  def gameMode(boolean: Boolean): Unit = {
    gamePane.getChildren.clear()
    menuPane.setVisible(!boolean)
    returnButton.setVisible(boolean)
    gamePane.setVisible(boolean)
  }

  def Connect4Start(): Unit = {
    gameMode(true)
    val gameEngine = new Connect4Engine
    gameEngine.startGame(gamePane)
  }

  def CheckersStart(): Unit = {
    gameMode(true)
    val gameEngine = new CheckersEngine
    gameEngine.startGame(gamePane)
  }

  def returnMenu(): Unit = {
    val media = new Media(new File("\\PaCSED\\YEAR 2\\Second Term\\Paradims\\BoardDrawingEngine\\src\\main\\scala\\Resources\\return.mp4").toURI.toString)
    val mediaPlayer = new MediaPlayer(media)
    mediaPlayer.setAutoPlay(true)
    val mediaView = new MediaView(mediaPlayer)
    mediaView.setTranslateX(175)
    mediaView.setTranslateY(150)
    var timeLine1: Timeline = new Timeline(
      new KeyFrame(Duration.millis(100), (e: Event) => menuPane.getChildren.add(mediaView)),
      new KeyFrame(Duration.millis(2700), (e: Event) => null)
    )
    timeLine1.setOnFinished((e: Event) => menuPane.getChildren.remove(mediaView))
    timeLine1.play()

    gameMode(false)
  }

}
