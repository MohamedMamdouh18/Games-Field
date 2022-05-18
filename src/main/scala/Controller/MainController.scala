package Controller

import javafx.fxml.{FXML, Initializable}
import javafx.scene.layout.AnchorPane

import java.net.URL
import java.util.ResourceBundle

class MainController extends Initializable with FxmlProxyGenerator.ProxyDependencyInjection{
  @FXML
  private var gamePane : AnchorPane ;

  override def initialize(url: URL, resourceBundle: ResourceBundle): Unit = {

  }

  def loadConnect() : Unit ={

  }

  def switchPanes() : Unit ={

  }

}
