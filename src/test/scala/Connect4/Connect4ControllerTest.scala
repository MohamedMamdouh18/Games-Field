package Connect4

import Base.{Piece, State}

class Connect4ControllerTest extends org.scalatest.FunSuite {
  var gameBoard: Array[Array[Piece]] = _
  var testBoard: Array[Array[Piece]] = _

  test("testMovementValidation") {
    gameBoard = Array(
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(
        null,
        new Piece(Connect4En.Red, 5, 1, 1),
        new Piece(Connect4En.Yellow, 5, 2, 0),
        null, null, null, null
      )
    )

    assert(Connect4Controller.movementValidation(gameBoard, new State(1, 1, -1, -1, 0)).valid)
    assert(Connect4Controller.movementValidation(gameBoard, new State(4, 1, -1, -1, 0)).valid)
    assert(Connect4Controller.movementValidation(gameBoard, new State(5, 5, -1, -1, 0)).valid)

  }

  test("testCheckEndGame") {
    gameBoard = Array(
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(
        new Piece(Connect4En.Yellow, 4, 0, 0),
        null,
        null,
        null,
        null,
        null,
        null
      ),
      Array(
        new Piece(Connect4En.Yellow, 5, 0, 0),
        new Piece(Connect4En.Yellow, 5, 1, 0),
        new Piece(Connect4En.Yellow, 5, 2, 0),
        new Piece(Connect4En.Red, 5, 3, 1),
        new Piece(Connect4En.Red, 5, 4, 1),
        new Piece(Connect4En.Red, 5, 5, 1),
        new Piece(Connect4En.Red, 5, 6, 1)
      )
    )

    assert(Connect4Controller.checkEndGame(gameBoard, 1))
  }

  test("testCheckNotEndGame") {
    gameBoard = Array(
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(
        null,
        new Piece(Connect4En.Red, 5, 1, 1),
        new Piece(Connect4En.Yellow, 5, 2, 0),
        null, null, null, null
      )
    )

    assert(!Connect4Controller.checkEndGame(gameBoard, 0))
  }

  test("testCheckNotTie") {
    gameBoard = Array(
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(null, null, null, null, null, null, null),
      Array(
        null,
        new Piece(Connect4En.Red, 5, 1, 1),
        new Piece(Connect4En.Yellow, 5, 2, 0),
        null, null, null, null
      )
    )

    assert(!Connect4Controller.checkTie(gameBoard, 0))
  }

  test("testCheckTie") {
    gameBoard = Array(
      Array(
        new Piece(Connect4En.Yellow, 0, 0, 0),
        new Piece(Connect4En.Red, 0, 1, 1),
        new Piece(Connect4En.Yellow, 0, 2, 0),
        new Piece(Connect4En.Red, 0, 3, 1),
        new Piece(Connect4En.Yellow, 0, 4, 0),
        new Piece(Connect4En.Red, 0, 5, 1),
        new Piece(Connect4En.Yellow, 0, 6, 0)
      ),
      Array(
        new Piece(Connect4En.Yellow, 1, 0, 0),
        new Piece(Connect4En.Red, 1, 1, 1),
        new Piece(Connect4En.Yellow, 1, 2, 0),
        new Piece(Connect4En.Yellow, 1, 3, 0),
        new Piece(Connect4En.Yellow, 1, 4, 0),
        new Piece(Connect4En.Red, 1, 5, 1),
        new Piece(Connect4En.Yellow, 1, 6, 0)
      ),
      Array(
        new Piece(Connect4En.Red, 2, 0, 1),
        new Piece(Connect4En.Yellow, 2, 1, 0),
        new Piece(Connect4En.Red, 2, 2, 1),
        new Piece(Connect4En.Red, 2, 3, 1),
        new Piece(Connect4En.Red, 2, 4, 1),
        new Piece(Connect4En.Yellow, 2, 5, 0),
        new Piece(Connect4En.Red, 2, 6, 1)
      ),
      Array(
        new Piece(Connect4En.Yellow, 3, 0, 0),
        new Piece(Connect4En.Red, 3, 1, 1),
        new Piece(Connect4En.Yellow, 3, 2, 0),
        new Piece(Connect4En.Yellow, 3, 3, 0),
        new Piece(Connect4En.Yellow, 3, 4, 0),
        new Piece(Connect4En.Red, 3, 5, 1),
        new Piece(Connect4En.Yellow, 3, 6, 0)
      ),
      Array(
        new Piece(Connect4En.Red, 4, 0, 1),
        new Piece(Connect4En.Yellow, 4, 1, 0),
        new Piece(Connect4En.Red, 4, 2, 1),
        new Piece(Connect4En.Red, 4, 3, 1),
        new Piece(Connect4En.Red, 4, 4, 1),
        new Piece(Connect4En.Yellow, 4, 5, 0),
        new Piece(Connect4En.Red, 4, 6, 1)
      ),
      Array(
        new Piece(Connect4En.Red, 5, 0, 1),
        new Piece(Connect4En.Yellow, 5, 1, 0),
        new Piece(Connect4En.Red, 5, 2, 1),
        new Piece(Connect4En.Yellow, 5, 3, 0),
        new Piece(Connect4En.Red, 5, 4, 1),
        new Piece(Connect4En.Yellow, 5, 5, 0),
        new Piece(Connect4En.Red, 5, 6, 1)
      )
    )

    assert(Connect4Controller.checkTie(gameBoard, 0))
  }

}
