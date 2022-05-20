package GameEngines.GamesControllers

class ChessController extends Controller {
  override var gameBoard: Array[Array[String]] = _
  var white: Boolean = true

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    true
  }
}
