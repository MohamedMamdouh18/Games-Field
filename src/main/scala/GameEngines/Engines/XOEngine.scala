package GameEngines.Engines

import GameEngines.Drawers.XODrawer
import GameEngines.GamesControllers.XOController

class XOEngine extends GameEngine {
  override val gameController = new XOController
  override val gameDrawer = new XODrawer
  override var gameBoard: Array[Array[String]] = Array(
    Array(".", ".", "."),
    Array(".", ".", "."),
    Array(".", ".", ".")
  )

}
