package GameEngines.Engines

import GameEngines.Drawers.Connect4Drawer
import GameEngines.GameEngine
import GameEngines.GamesControllers.Connect4Controller
import javafx.scene.paint.Color

class Connect4Engine extends GameEngine {
  override val gameController = new Connect4Controller
  override val gameDrawer = new Connect4Drawer
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](6, 7)


  //    var gameOver=false
//    while(!gameOver){
//      gameController.draw()
//      gameController.checking( , gameController.turn)
//      gameController.changeTurns()
//    }

}
