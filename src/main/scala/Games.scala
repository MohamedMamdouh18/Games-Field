import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafxml.core.{FXMLView, NoDependencyResolver}

import java.io.IOException

object Games extends JFXApp3 {
  override def start(): Unit = {
    val fxml = getClass.getResource("Resources/mainView.fxml")
    if (fxml == null) {
      throw new IOException("Cannot load resource: AdoptionForm.fxml")
    }
    val root = FXMLView(fxml, NoDependencyResolver)

    stage = new JFXApp3.PrimaryStage {
      title.value = "Hello Stage"
      width = 1080
      height = 770
      scene = new Scene(root)
    }
  }
}