import javafx.scene.image.Image
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafxml.core.{FXMLView, NoDependencyResolver}

import java.io.IOException

object Games extends JFXApp3 {
  /**
   * Starts the App.
   */
  override def start(): Unit = {
    val fxml = getClass.getResource("Resources/mainView.fxml")
    if (fxml == null) {
      throw new IOException("Cannot load resource: AdoptionForm.fxml")
    }
    val root = FXMLView(fxml, NoDependencyResolver)

    stage = new JFXApp3.PrimaryStage {
      title.value = "Game Engine"
      width = 1080
      height = 800
      scene = new Scene(root)
    }
    stage.getIcons.add(new Image("Resources/icon.png"))
  }
}