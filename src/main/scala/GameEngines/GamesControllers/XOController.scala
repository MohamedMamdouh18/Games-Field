package GameEngines.GamesControllers

class XOController extends Controller {
  override var gameBoard: Array[Array[String]] = Array.ofDim[String](3, 3)
  var turn = true

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    if (gameBoard(oldCol)(oldRow) == ".") {
      if (this.turn)
        gameBoard(oldCol)(oldRow) = "X"
      else
        gameBoard(oldCol)(oldRow) = "O"

      this.turn = !this.turn

      true
    } else
      false
  }
}
