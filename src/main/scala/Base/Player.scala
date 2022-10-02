package Base

import javafx.scene.Node
import javafx.scene.layout.GridPane

abstract class Player {
  var observer: GameEngine
  var color: Int = 0
  var depth: Int = 0

  def run(buts: GridPane = null): Unit

  def setDepth(dep: Int): Unit = {
    depth = dep
  }

  def Notify(): Unit = {
    observer.update()
  }

  def Movement(source: Node = null): Unit = {}

  def DisableMovement(): Unit = {}

  def setObserver(gameEngine: GameEngine): Unit = {
    observer = gameEngine
  }
}
