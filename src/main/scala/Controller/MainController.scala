package Controller

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class MainController() {

  def handleSubmit(event: ActionEvent): Unit = {
    println("XO")
  }

}
