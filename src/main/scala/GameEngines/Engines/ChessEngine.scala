package GameEngines.Engines

import GameEngines.Drawers.ChessDrawer
import GameEngines.GameEngine
import GameEngines.GamesControllers.ChessController

class ChessEngine extends GameEngine {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
}
