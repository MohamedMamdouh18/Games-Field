package Base.Player

import Base.{Controller, Drawer, GameEngine, Piece, State}
import javafx.scene.Node
import javafx.scene.layout.GridPane

abstract class Player {
  var observer: GameEngine
  var color: Int = 0

  def run(buts: GridPane = null): Unit

  def Notify(state: State = null): Unit = {
    if (state != null)
      observer.update(state)
    else observer.update()
  }

  def Movement(source: Node = null): Unit

  def DisableMovement(): Unit = {
    observer.gameDrawer.gameBoard.getChildren.forEach(child => {
      child.setOnMouseClicked(null)
    })
  }

  def setObserver(gameEngine: GameEngine): Unit = {
    observer = gameEngine
  }
}
