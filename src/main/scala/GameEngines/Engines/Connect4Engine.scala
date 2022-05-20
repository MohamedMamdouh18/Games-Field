package GameEngines.Engines

import GameEngines.Drawers.Connect4Drawer
import GameEngines.GamesControllers.Connect4Controller

class Connect4Engine extends GameEngine {
  override val gameController = new Connect4Controller
  override val gameDrawer = new Connect4Drawer
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](6, 7)
}
