package Chess

import Base.{GameEngine, Piece}
import javafx.scene.Node

class ChessEngine extends GameEngine {
  override val gameController = new ChessController
  override val gameDrawer = new ChessDrawer
  override var gameBoard: Array[Array[Piece]] = Array.ofDim[Piece](8, 8)

  override def Movement(source: Node): Unit = {

  }
}
