package GameEngines.Engines

import GameEngines.Drawers.CheckersDrawer
import GameEngines.GamesControllers.CheckersController

class CheckersEngine extends GameEngine {
  override val gameController = new CheckersController
  override val gameDrawer = new CheckersDrawer
  override var gameBoard: Array[Array[String]] = Array(
    Array("A", "B", "C", "D", "E", "F", "G", "H"),
    Array("y", ".", "y", ".", "y", ".", "y", ".", "1"),
    Array(".", "y", ".", "y", ".", "y", ".", "y", "2"),
    Array("y", ".", "y", ".", "y", ".", "y", ".", "3"),
    Array("-", ".", "-", ".", "-", ".", "-", ".", "4"),
    Array(".", "-", ".", "-", ".", "-", ".", "-", "5"),
    Array("-", "x", "-", "x", "-", "x", "-", "x", "6"),
    Array("x", ".", "x", ".", "x", ".", "x", ".", "7"),
    Array(".", "x", ".", "x", ".", "x", ".", "x", "8"),
  )
}
