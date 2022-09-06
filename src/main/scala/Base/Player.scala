package Base

import javafx.scene.Node
import javafx.scene.layout.GridPane

abstract class Player {
  var gameController: Controller
  var gameDrawer: Drawer
  var gameBoard: Array[Array[Piece]]
  var observer: GameEngine
  var color: Int = 0
  var turn: Array[Int]

  def run(board: Array[Array[Piece]], turn: Array[Int],
          controller: Controller, drawer: Drawer, buts: GridPane = null): Unit

  def Notify(): Unit = {
    observer.update()
  }

  def Movement(source: Node): Unit

  def DisableMovement(): Unit = {
    gameDrawer.gameBoard.getChildren.forEach(child => {
      child.setOnMouseClicked(null)
    })
  }

  def setObserver(gameEngine: GameEngine): Unit = {
    observer = gameEngine
  }
}
