package Connect4

import Base.{GameEngine, Piece, Player}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class Connect4Player extends Player {

  override def run(buts: GridPane = null): Unit = {
    gameBoard = observer.gameBoard
  }

  override def DisableMovement(): Unit = {
    Connect4Drawer.gameBoard.getChildren.forEach(child => {
      child.setOnMouseClicked(null)
    })
  }
}
