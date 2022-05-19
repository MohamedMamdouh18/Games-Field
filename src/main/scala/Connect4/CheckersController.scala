package Connect4

import scala.util.control.Breaks.{break, breakable}

object CheckersController {
  var board = Array(
    Array("A","B","C","D","E","F","G","H"),
    Array("-", "y", "-","y","-","y","-","y","1"),
    Array("y", ".", "y",".","y",".","y",".","2"),
    Array(".", "y", ".","y",".","y",".","y","3"),
    Array("-", ".", "-",".","-",".","-",".","4"),
    Array(".", "-", ".","-",".","-",".","-","5"),
    Array("x", ".", "x",".","x",".","x",".","6"),
    Array(".", "x", ".","x",".","x",".","x","7"),
    Array("x", ".", "x",".","x",".","x",".","8")
  )
  var Frompiece = new Array[Char](2)
  var To_piece = new Array[Char](2)
  var Player_turn=1
  var fromPiece = "x"
  var eaten=false
  var change_player=false
  var promoted : Map[Int,Int]=Map()
  var prompotion=false
  val check = new Checking_Moves()
  val move=new Move()
  val draw = new DRAW()
  def main(args: Array[String]): Unit = {
      while (true) {
        var noplayerchange = false
        println("Player turn:" + Player_turn)
        draw.draw()
        val name = scala.io.StdIn.readLine("Enter the index of the piece you want to move: ")
        for(i<-0 to 1)
        {
            Frompiece(i)=name(i)
        }
        if ( name.length > 2)
        {
          printf("Enter the index in the correct form\n");
        }
        else
        {
          if(check.validSt(Frompiece))
          {
            if(check.validFrom())
            {
              breakable {
                while (true) {
                  val name2 = scala.io.StdIn.readLine("Enter the index of the square you want to move you piece to: ")
                  for (i <- 0 to 1) {
                    To_piece(i) = name2(i)
                  }
                  if (name2.length > 2) {
                    printf("Enter the index in the correct form\n");
                  }
                  else {
                    if (check.validSt(To_piece)) {
                      if (check.validto()) {
                        move.move()
                        break
                      }
                      else {
                        printf("Enter valid move to your piece\n")
                      }
                    }
                    else {
                      printf("Enter the index in the correct form\n")
                    }
                  }
                }
              }
            }
            else
              {
                printf("Enter the index of one of your pieces\n")
                noplayerchange=true
              }
          }
          else
            {
              printf("Enter the index in the correct form\n")
              noplayerchange=true
            }
        }
        if (!change_player && !noplayerchange )
        {
          if(Player_turn==1)
          {
            Player_turn=2
          }
          else
          {
            Player_turn=1
          }
        }
      }
  }
}