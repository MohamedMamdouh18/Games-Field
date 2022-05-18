import javafx.fxml.FXMLLoader
import scalafx.Includes.when
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

import java.util.PropertyResourceBundle
import javafx.fxml.FXMLLoader
import javafx.{scene => jfxs}

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

object Games extends JFXApp3 {
  override def start(): Unit = {
    val fxml = getClass.getClassLoader.getResource("Resources/mainView.fxml")
    val root: jfxs.Parent = FXMLLoader.load(fxml)

    stage = new JFXApp3.PrimaryStage {
      title.value = "Hello Stage"
      width = 600
      height = 450
      scene = new Scene(root)
    }
  }
}