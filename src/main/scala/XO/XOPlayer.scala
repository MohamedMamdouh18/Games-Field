package XO

import Base.Player
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
