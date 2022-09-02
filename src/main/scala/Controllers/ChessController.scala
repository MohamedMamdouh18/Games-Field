package Controllers

import ChessPieces._

class ChessController extends Controller {
  override var gameBoard: Array[Array[String]] = _
  ChessController.board = Array(
    Array(new Castle("bcas", 0, 0, 1),
      new Knight("bhor", 0, 1, 1),
      new Bishop("bele", 0, 2, 1),
      new Queen("bque", 0, 3, 1),
      new King("bkin", 0, 4, 1),
      new Bishop("bele", 0, 5, 1),
      new Knight("bhor", 0, 6, 1),
      new Castle("bcas", 0, 7, 1)),

    Array(new Soldier("bsol", 1, 0, 1),
      new Soldier("bsol", 1, 1, 1),
      new Soldier("bsol", 1, 2, 1),
      new Soldier("bsol", 1, 3, 1),
      new Soldier("bsol", 1, 4, 1),
      new Soldier("bsol", 1, 5, 1),
      new Soldier("bsol", 1, 6, 1),
      new Soldier("bsol", 1, 7, 1)),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(null, null, null, null, null, null, null, null),

    Array(new Soldier("wsol", 6, 0, 0),
      new Soldier("wsol", 6, 1, 0),
      new Soldier("wsol", 6, 2, 0),
      new Soldier("wsol", 6, 3, 0),
      new Soldier("wsol", 6, 4, 0),
      new Soldier("wsol", 6, 5, 0),
      new Soldier("wsol", 6, 6, 0),
      new Soldier("wsol", 6, 7, 0)),

    Array(new Castle("wcas", 7, 0, 0),
      new Knight("whor", 7, 1, 0),
      new Bishop("wele", 7, 2, 0),
      new Queen("wque", 7, 3, 0),
      new King("wkin", 7, 4, 0),
      new Bishop("wele", 7, 5, 0),
      new Knight("whor", 7, 6, 0),
      new Castle("wcas", 7, 7, 0)),
  )

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    true
  }
}


object ChessController {
  var board: Array[Array[Piece]]  = null
}
