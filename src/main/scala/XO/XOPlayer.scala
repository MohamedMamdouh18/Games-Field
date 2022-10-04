package XO

import Base.{GameEngine, Piece, Player}
import javafx.scene.Node
import javafx.scene.layout.GridPane

class XOPlayer extends Player {

  override def run(buts: GridPane = null): Unit = {
    gameBoard = observer.gameBoard
  }

  override def DisableMovement(): Unit = {
    XODrawer.gameBoard.getChildren.forEach(child => {
      child.setOnMouseClicked(null)
    })
  }
}
