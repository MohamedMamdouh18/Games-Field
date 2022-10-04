package XO

import Base.{Piece, State}

class XOControllerTest extends org.scalatest.FunSuite {

  var gameBoard: Array[Array[Piece]] = _
  var testBoard: Array[Array[Piece]] = _

  test("testMovementValidation") {
    gameBoard = Array(
      Array(new Piece(XOEn.X, 0, 0, 0), null, null),
      Array(null, null, null),
      Array(null, null, new Piece(XOEn.O, 2, 2, 1)),
    )

    assert(XOController.movementValidation(gameBoard, new State(1, 1, -1, -1, 0)).valid)
    assert(XOController.movementValidation(gameBoard, new State(0, 1, -1, -1, 0)).valid)
    assert(XOController.movementValidation(gameBoard, new State(2, 1, -1, -1, 0)).valid)

  }

  test("testCheckEndGame") {
    gameBoard = Array(
      Array(new Piece(XOEn.X, 0, 0, 0), new Piece(XOEn.O, 0, 1, 1), new Piece(XOEn.O, 0, 2, 1)),
      Array(null, new Piece(XOEn.X, 1, 1, 0), null),
      Array(null, null, new Piece(XOEn.X, 2, 2, 0)),
    )

    assert(XOController.checkEndGame(gameBoard))
  }

  test("testCheckNotEndGame") {
    gameBoard = Array(
      Array(new Piece(XOEn.O, 0, 1, 1), null, new Piece(XOEn.X, 0, 2, 0)),
      Array(new Piece(XOEn.X, 1, 0, 0), new Piece(XOEn.O, 1, 1, 1), new Piece(XOEn.O, 1, 2, 1)),
      Array(new Piece(XOEn.O, 2, 0, 1), new Piece(XOEn.X, 2, 1, 0), new Piece(XOEn.X, 2, 2, 0)),
    )

    assert(!XOController.checkEndGame(gameBoard))
  }

  test("testCheckNotTie") {
    gameBoard = Array(
      Array(new Piece(XOEn.X, 0, 0, 0), new Piece(XOEn.O, 0, 1, 1), new Piece(XOEn.O, 0, 2, 1)),
      Array(null, null, null),
      Array(null, null, new Piece(XOEn.X, 2, 2, 0)),
    )

    assert(!XOController.checkTie(gameBoard))
  }

  test("testCheckTie") {
    gameBoard = Array(
      Array(new Piece(XOEn.O, 0, 1, 1), new Piece(XOEn.X, 0, 1, 0), new Piece(XOEn.X, 0, 2, 0)),
      Array(new Piece(XOEn.X, 1, 0, 0), new Piece(XOEn.O, 1, 1, 1), new Piece(XOEn.O, 1, 2, 1)),
      Array(new Piece(XOEn.O, 2, 0, 1), new Piece(XOEn.X, 2, 1, 0), new Piece(XOEn.X, 2, 2, 0)),
    )

    assert(XOController.checkTie(gameBoard))
  }

}
