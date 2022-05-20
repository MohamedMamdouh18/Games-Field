package GameEngines.Engines

import GameEngines.Drawers.ChessDrawer
import GameEngines.GameEngine
import GameEngines.GamesControllers.ChessController

class ChessEngine extends GameEngine {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](8, 8)
}
