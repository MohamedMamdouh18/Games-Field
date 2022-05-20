package GameEngines.GamesControllers

import Connect4.CheckersController.{board, eaten}
import javafx.scene.Node
import javafx.scene.layout.GridPane
class CheckersController extends Controller {
  var boardData = Array(
    Array("A","B","C","D","E","F","G","H"),
    Array("y", ".", "y", ".", "y", ".", "y", ".","1"),
    Array(".", "y", ".", "y", ".", "y", ".", "y","2"),
    Array("y", ".", "y", ".", "y", ".", "y", ".","3"),
    Array("-", ".", "-", ".", "-", ".", "-", ".","4"),
    Array(".", "-", ".", "-", ".", "-", ".", "-","5"),
    Array("-", "x", "-", "x", "-", "x", "-", "x","6"),
    Array("x", ".", "x", ".", "x", ".", "x", ".","7"),
    Array(".", "x", ".", "x", ".", "x", ".", "x","8"),
  )
  var Player_turn=1
  var fromPiece = "x"
  var eaten=false
  var change_player=false
  var promoted : Map[Int,Int]=Map()
  var prompotion=false
  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int,color:String): Boolean = {
    println(oldCol + " " + oldRow + " " + newCol + " " + newRow)
    val to_x = newCol
    val to_y = newRow+1
    val fromX = oldCol
    var fromY = oldRow+1
    if(color=="white")
      {
        Player_turn=1
      }
    else
    {
        Player_turn=2
    }
    if(validFrom(fromY,fromX))
    {
      println("lol")
      if (validto(to_y,to_x,fromY,fromX)) {
        println("e4ta")
         move(to_y,to_x,fromY,fromX)
        for(i<- 0 to 8)
        {
          for(j<- 0 to boardData(i).length-1)
          {
            print(" "+boardData(i)(j))
          }
          println()
        }
        return true
      }
      else
        {
          return false
        }
    }
    else
      {
        return false
      }
  }
  def validFrom(fromY:Int,fromX:Int):Boolean={
    println(boardData(fromY)(fromX))
    println(Player_turn)
    if(Player_turn==1)
    {
      if(boardData(fromY)(fromX)=="x")
      {

        fromPiece = boardData(fromY)(fromX)
        return true
      }
    }else
    {
      if(boardData(fromY)(fromX)=="y")
      {
        fromPiece = boardData(fromY)(fromX)
        return true
      }
    }
    return false
  }
  def validto(to_y:Int,to_x:Int,fromY:Int,fromX:Int):Boolean={
    println(to_y)
    println(to_x)
    println(fromY)
    println(fromX)
    println(boardData(fromY)(fromX))
    if(Player_turn==1)
    {
      if(boardData(to_y)(to_x)=="x")
      {
        return false
      }
    }
    else
    {
      if(boardData(to_y)(to_x)=="y")
      {
        return false
      }
    }
    if(Player_turn==1)
    {
      if(promoted.contains(fromY))
      {
        if ((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1) ||(to_y==fromY+1 && to_x==fromX+1) || (to_y==fromY+1 && to_x==fromX-1)  ) {
          prompotion=true
          return true
        }
        else if((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2) || (to_y==fromY+2 && to_x==fromX+2) || (to_y==fromY+2 && to_x==fromX-2) )
        {
          if (boardData(fromY - 1)(fromX + 1) == "y" || boardData(fromY - 1)(fromX - 1) == "y") {
            eaten = true
            prompotion=true
            return true
          }
          else {
            prompotion=false
            return false
          }
        }
      }
      else {
        if ((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1)) {
          return true
        }
        else if ((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2)) {
          if (boardData(fromY - 1)(fromX + 1) == "y" || boardData(fromY - 1)(fromX - 1) == "y") {
            eaten = true
            return true
          }
          else {
            return false
          }
        }
      }
    }
    else
    {
      if(promoted.contains(fromY))
      {
        if ((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1) ||(to_y==fromY+1 && to_x==fromX+1) || (to_y==fromY+1 && to_x==fromX-1)  ) {
          prompotion=true
          return true
        }
        else if((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2) || (to_y==fromY+2 && to_x==fromX+2) || (to_y==fromY+2 && to_x==fromX-2) )
        {
          if (boardData(fromY - 1)(fromX + 1) == "x" || boardData(fromY - 1)(fromX - 1) == "x") {
            eaten = true
            prompotion=true
            return true
          }
          else {
            prompotion=false
            return false
          }
        }
      }
      else {
        if ((to_y == fromY + 1 && to_x == fromX + 1) || (to_y == fromY + 1 && to_x == fromX - 1)) {
          return true
        }
        else if ((to_y == fromY + 2 && to_x == fromX + 2) || (to_y == fromY + 2 && to_x == fromX - 2)) {
          if (boardData(fromY + 1)(fromX + 1) == "x" || boardData(fromY - 1)(fromX + 1) == "x") {
            eaten = true
            return true
          }
          else {
            return false
          }
        }
      }
    }
    return false
  }
  def move(to_y:Int,to_x:Int,fromY:Int,fromX:Int)={

    boardData(to_y)(to_x)=boardData(fromY)(fromX)
    if(Player_turn==1)
    {
      if(to_y==1)
      {
        promoted = promoted+ (to_y->to_x)
      }
    }
    else
    {
      if(to_y==8)
      {
        promoted = promoted+ (to_y->to_x)
      }
    }
    if(eaten)
    {
      if(to_y<fromY && to_x < fromX )
      {
        if((fromY +fromX)%2==0)
        {
          boardData(fromY-1)(fromX-1)="-"
        }
        else
        {
          boardData(fromY-1)(fromX-1)="."
        }
        if(to_x!=0 && to_y!=0)
        {
          if(boardData(to_y-1)(to_x-1)=="y" && (boardData(to_y-2)(to_x-2)=="." || boardData(to_y-2)(to_x-2)=="-")  || boardData(to_y-1)(to_x+1)=="y" && (boardData(to_y-2)(to_x+2)=="." || boardData(to_y-2)(to_x+2)=="-"))
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
        else if(to_y!=0)
        {
          if(boardData(to_y-1)(to_x+1)=="y" && (boardData(to_y-2)(to_x+2)=="." || boardData(to_y-2)(to_x+2)=="-"))
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }

      }
      else if(to_y<fromY && to_x>fromX)
      {
        if((fromY +fromX)%2==0)
        {
          boardData(fromY-1)(fromX+1)="-"
        }
        else
        {
          boardData(fromY-1)(fromX+1)="."
        }
        if(to_x!=0 && to_y!=0)
        {
          if(boardData(to_y-1)(to_x-1)=="y" && (boardData(to_y-2)(to_x-2)=="." || boardData(to_y-2)(to_x-2)=="-")  || boardData(to_y-1)(to_x+1)=="y" && (boardData(to_y-2)(to_x+2)=="." || boardData(to_y-2)(to_x+2)=="-"))
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
        else if(to_y!=0)
        {
          if(boardData(to_y-1)(to_x+1)=="y" && (boardData(to_y-2)(to_x+2)=="." || boardData(to_y-2)(to_x+2)=="-"))
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
      }
      else if(to_y>fromY && to_x>fromX)
      {
        if((fromY +fromX)%2==0)
        {
          boardData(fromY+1)(fromX+1)="-"
        }
        else
        {
          boardData(fromY+1)(fromX+1)="."
        }
        if(to_x!=0)
        {
          if((boardData(to_y+1)(to_x+1)=="y" && (boardData(to_y+2)(to_x+2)=="." || boardData(to_y+2)(to_x+2)=="-")) || boardData(to_y+1)(to_x-1)=="y" && (boardData(to_y+2)(to_x-2)=="." || boardData(to_y+2)(to_x-2)=="-") )
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
        else
        {
          if(boardData(to_y+1)(to_x+1)=="y" && (boardData(to_y+2)(to_x+2)=="." || boardData(to_y+2)(to_x+2)=="-"))
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
      }
      else
      {
        if((fromY +fromX)%2==0)
        {
          boardData(fromY+1)(fromX-1)="-"
        }
        else
        {
          boardData(fromY+1)(fromX-1)="."
        }
        if(to_x!=0)
        {
          if((boardData(to_y+1)(to_x+1)=="y" && (boardData(to_y+2)(to_x+2)=="." || boardData(to_y+2)(to_x+2)=="-")) || boardData(to_y+1)(to_x-1)=="y" && (boardData(to_y+2)(to_x-2)=="." || boardData(to_y+2)(to_x-2)=="-") )
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
        else
        {
          if(boardData(to_y+1)(to_x+1)=="y" && (boardData(to_y+2)(to_x+2)=="." || boardData(to_y+2)(to_x+2)=="-"))
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
      }
      if(prompotion)
      {
        if(to_x!=0 && to_y!=0)
        {
          if(boardData(to_y+1)(to_x-1)=="y" && (boardData(to_y+2)(to_x-2)=="." || boardData(to_y+2)(to_x-2)=="-") || boardData(to_y+1)(to_x+1)=="y" && (boardData(to_y+2)(to_x+2)=="." || boardData(to_y+2)(to_x+2)=="-") ||boardData(to_y-1)(to_x+1)=="y" && (boardData(to_y-2)(to_x+2)=="." || boardData(to_y-2)(to_x+2)=="-") || boardData(to_y-1)(to_x-1)=="y" && (boardData(to_y-2)(to_x-2)=="." || boardData(to_y-2)(to_x-2)=="-") )
          {
            change_player=true
          }
          else
          {
            change_player=false
          }
        }
      }
      eaten=false
    }
    if(prompotion==true)
    {
      promoted= promoted.removed(fromY)
      promoted=promoted+(to_y->to_x)
      prompotion=false
    }
    if((fromY +fromX)%2==0)
    {
      boardData(fromY)(fromX)="-"
    }
    else
    {
      boardData(fromY)(fromX)="."
    }

  }
}
