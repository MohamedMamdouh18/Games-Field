package GameEngines.GamesControllers

import javafx.scene.layout.GridPane

abstract class Controller {
  def movementValidation(board: GridPane): Boolean = ???
  def movement(board: GridPane): Unit = ???
}
