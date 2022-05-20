package GameEngines.Engines

import GameEngines.Drawers.{CheckersDrawer}
import GameEngines.GameEngine
import GameEngines.GamesControllers.{CheckersController}

class CheckersEngine extends GameEngine {
  override val gameController = new CheckersController
  override val gameDrawer = new CheckersDrawer
/*
  override def movementCheck():Boolean={
    return true
  }
*/
 def move():Unit={

 }
}
