package Chess

import Base.{Piece, State}
import Chess.Pieces._
import org.scalatest.funsuite.AnyFunSuite

class ChessControllerTest extends AnyFunSuite {
  var gameBoard: Array[Array[Piece]] = _
  var testBoard: Array[Array[Piece]] = _

  test("testRestoreState") {
    gameBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    testBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(
        new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    val state: State = new State(6, 3, 4, 3, 0)

    val removed = ChessController.createState(gameBoard, gameBoard(6)(3), 4, 3)

    ChessController.restoreState(gameBoard, gameBoard(4)(3), removed, state)

    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        assert(!((gameBoard(i)(j) == null && testBoard(i)(j) != null) ||
          (gameBoard(i)(j) != null && testBoard(i)(j) == null)))
        if (gameBoard(i)(j) != null && testBoard(i)(j) != null)
          assert(gameBoard(i)(j).name == testBoard(i)(j).name)
      }
    }
  }

  test("testCreateState") {
    gameBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    testBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, new Knight(ChessEn.WhiteKnight, 5, 7, 0)),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(
        new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        null,
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    ChessController.createState(gameBoard, gameBoard(7)(6), 5, 7)

    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        assert(!((gameBoard(i)(j) == null && testBoard(i)(j) != null) ||
          (gameBoard(i)(j) != null && testBoard(i)(j) == null)))
        if (gameBoard(i)(j) != null && testBoard(i)(j) != null)
          assert(gameBoard(i)(j).name == testBoard(i)(j).name)
      }
    }
  }

  test("testMovementValidationAndMoveIsRight") {
    gameBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    val state1: State = new State(7, 6, 5, 5, 0)
    val state2: State = new State(6, 4, 4, 4, 0)
    val state3: State = new State(0, 6, 2, 5, 1)

    assert(ChessController.movementValidation(gameBoard, state1).valid)
    assert(ChessController.movementValidation(gameBoard, state2).valid)
    assert(ChessController.movementValidation(gameBoard, state3).valid)
  }

  test("testMovementValidationAndMoveIsRightWithEating") {
    gameBoard = Array(
      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Rook(ChessEn.BlackRook, 5, 0, 1),
        new Knight(ChessEn.BlackKnight, 5, 1, 1),
        new Bishop(ChessEn.BlackBishop, 5, 2, 1),
        new Queen(ChessEn.BlackQueen, 5, 3, 1),
        new King(ChessEn.BlackKing, 5, 4, 1),
        new Bishop(ChessEn.BlackBishop, 5, 5, 1),
        new Knight(ChessEn.BlackKnight, 5, 6, 1),
        new Rook(ChessEn.BlackRook, 5, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    val state1: State = new State(7, 6, 5, 5, 0)
    val state2: State = new State(7, 3, 5, 3, 0)
    val state3: State = new State(5, 6, 7, 5, 1)

    assert(ChessController.movementValidation(gameBoard, state1).valid)
    assert(ChessController.movementValidation(gameBoard, state2).valid)
    assert(ChessController.movementValidation(gameBoard, state3).valid)
  }

  test("testMovementValidationAndMoveIsWrong") {
    gameBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    val state1: State = new State(7, 6, 6, 4, 0)
    val state2: State = new State(7, 6, 9, 10, 0)
    val state3: State = new State(7, 7, 6, 7, 1)
    val state4: State = new State(-1, -1, 5, 6, 0)

    assert(!ChessController.movementValidation(gameBoard, state1).valid)
    assert(!ChessController.movementValidation(gameBoard, state2).valid)
    assert(!ChessController.movementValidation(gameBoard, state3).valid)
    assert(!ChessController.movementValidation(gameBoard, state4).valid)
  }

  test("testFindKing") {
    gameBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    assert(ChessController.findKing(gameBoard, 0).name == ChessEn.WhiteKing)
    assert(ChessController.findKing(gameBoard, 1).name == ChessEn.BlackKing)
  }

  test("testKingCastling") {
    gameBoard = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        null,
        null,
        null,
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        null,
        null,
        null,
        new King(ChessEn.WhiteKing, 7, 4, 0),
        null,
        null,
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    val state1: State = new State(0, 4, 0, 2, 1)
    val state2: State = new State(7, 4, 7, 2, 0)
    val state3: State = new State(7, 4, 7, 6, 0)

    assert(ChessController.kingCastling(gameBoard, state1) == 3)
    assert(ChessController.kingCastling(gameBoard, state2) == 3)
    assert(ChessController.kingCastling(gameBoard, state3) == 5)
  }

  test("testCheckMate") {
    val gameBoard1: Array[Array[Piece]] = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        null,
        null,
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null,
        new Pawn(ChessEn.BlackPawn, 3, 5, 1), new Pawn(ChessEn.BlackPawn, 3, 6, 1),
        new Queen(ChessEn.WhiteQueen, 3, 7, 0)),

      Array(null, null, new Bishop(ChessEn.WhiteBishop, 4, 2, 0), null,
        new Pawn(ChessEn.WhitePawn, 4, 4, 0), null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        null,
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        null,
        new King(ChessEn.WhiteKing, 7, 4, 0),
        null,
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )
    val gameBoard2: Array[Array[Piece]] = Array(
      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Rook(ChessEn.BlackRook, 7, 0, 1),
        null,
        null,
        null,
        new King(ChessEn.WhiteKing, 7, 4, 0),
        null,
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    assert(ChessController.checkMate(gameBoard1, 1))
    assert(ChessController.checkMate(gameBoard2, 0))
  }

  test("testCheckEndGame") {
    val gameBoard1: Array[Array[Piece]] = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        null,
        null,
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null,
        new Pawn(ChessEn.BlackPawn, 3, 5, 1), new Pawn(ChessEn.BlackPawn, 3, 6, 1),
        new Queen(ChessEn.WhiteQueen, 3, 7, 0)),

      Array(null, null, new Bishop(ChessEn.WhiteBishop, 4, 2, 0), null,
        new Pawn(ChessEn.WhitePawn, 4, 4, 0), null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        null,
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        null,
        new King(ChessEn.WhiteKing, 7, 4, 0),
        null,
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )
    val gameBoard2: Array[Array[Piece]] = Array(
      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, new Queen(ChessEn.BlackQueen, 6, 7, 1)),

      Array(
        new Rook(ChessEn.BlackRook, 7, 0, 1),
        null,
        null,
        null,
        new King(ChessEn.WhiteKing, 7, 4, 0),
        null,
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    assert(ChessController.checkEndGame(gameBoard1, 1))
    assert(ChessController.checkEndGame(gameBoard2, 0))
  }

  test("testCheckNotEndGame") {
    val gameBoard1: Array[Array[Piece]] = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        new Queen(ChessEn.BlackQueen, 0, 3, 1),
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        new Pawn(ChessEn.BlackPawn, 1, 1, 1),
        new Pawn(ChessEn.BlackPawn, 1, 2, 1),
        new Pawn(ChessEn.BlackPawn, 1, 3, 1),
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        new Pawn(ChessEn.WhitePawn, 6, 4, 0),
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )
    val gameBoard2: Array[Array[Piece]] = Array(
      Array(
        new Rook(ChessEn.BlackRook, 0, 0, 1),
        new Knight(ChessEn.BlackKnight, 0, 1, 1),
        new Bishop(ChessEn.BlackBishop, 0, 2, 1),
        null,
        new King(ChessEn.BlackKing, 0, 4, 1),
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        null,
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        new Pawn(ChessEn.BlackPawn, 1, 0, 1),
        null,
        null,
        null,
        null,
        new Pawn(ChessEn.BlackPawn, 1, 5, 1),
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Pawn(ChessEn.BlackPawn, 1, 7, 1)
      ),

      Array(null, new Queen(ChessEn.BlackQueen, 2, 1, 1), null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(null, null, null, null, null, null, null, null),

      Array(new Bishop(ChessEn.WhiteBishop, 5, 0, 0), null, null, null, null, null, null, new Knight(ChessEn.WhiteKnight, 5, 7, 0)),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        null,
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        null,
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        null,
        new Pawn(ChessEn.WhitePawn, 6, 7, 0)
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        null,
        new Queen(ChessEn.WhiteQueen, 7, 3, 0),
        null,
        new Rook(ChessEn.WhiteRook, 7, 5, 0),
        new King(ChessEn.WhiteKing, 7, 6, 0),
        null
      ),
    )

    assert(!ChessController.checkEndGame(gameBoard1, 1))
    assert(!ChessController.checkEndGame(gameBoard2, 1))
  }

  test("testCheckTie") {
    gameBoard = Array(
      Array(
        null,
        null,
        null,
        null,
        null,
        new Bishop(ChessEn.BlackBishop, 0, 5, 1),
        new Knight(ChessEn.BlackKnight, 0, 6, 1),
        new Rook(ChessEn.BlackRook, 0, 7, 1)
      ),

      Array(
        null,
        null,
        null,
        null,
        new Pawn(ChessEn.BlackPawn, 1, 4, 1),
        null,
        new Pawn(ChessEn.BlackPawn, 1, 6, 1),
        new Queen(ChessEn.BlackQueen, 1, 7, 1)
      ),

      Array(null, null, null, null, new Queen(ChessEn.WhiteQueen, 2, 4, 0),
        new Pawn(ChessEn.BlackPawn, 2, 5, 1), new King(ChessEn.BlackKing, 2, 6, 1), new Rook(ChessEn.BlackRook, 2, 7, 1)),

      Array(null, null, null, null, null, null, null, new Pawn(ChessEn.BlackPawn, 3, 7, 1)),

      Array(null, null, null, null, null, null, null, new Pawn(ChessEn.WhitePawn, 4, 7, 0)),

      Array(null, null, null, null, new Pawn(ChessEn.WhitePawn, 5, 4, 0), null, null, null),

      Array(
        new Pawn(ChessEn.WhitePawn, 6, 0, 0),
        new Pawn(ChessEn.WhitePawn, 6, 1, 0),
        new Pawn(ChessEn.WhitePawn, 6, 2, 0),
        new Pawn(ChessEn.WhitePawn, 6, 3, 0),
        null,
        new Pawn(ChessEn.WhitePawn, 6, 5, 0),
        new Pawn(ChessEn.WhitePawn, 6, 6, 0),
        null
      ),

      Array(new Rook(ChessEn.WhiteRook, 7, 0, 0),
        new Knight(ChessEn.WhiteKnight, 7, 1, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 2, 0),
        null,
        new King(ChessEn.WhiteKing, 7, 4, 0),
        new Bishop(ChessEn.WhiteBishop, 7, 5, 0),
        new Knight(ChessEn.WhiteKnight, 7, 6, 0),
        new Rook(ChessEn.WhiteRook, 7, 7, 0)
      ),
    )

    assert(ChessController.checkTie(gameBoard, 1))
  }
}
