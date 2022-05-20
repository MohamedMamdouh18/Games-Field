package GameEngines.GamesControllers
import javafx.collections.ObservableList
import Connect4.CheckersController.{board, eaten}
import javafx.scene.Node


import javafx.scene.shape.Circle
import javafx.scene.layout.GridPane

import scala.util.control.Breaks.{break, breakable}
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
  var x_8:Int=0
  var y_8:Int=0
  var eat=false
  var Player_turn=1
  var fromPiece = "x"
  var eaten=false
  var change_player=false
  var promoted : Map[Int,Int]=Map()
  var prompotion=false
  var noplayerchange = false

  override def movementValidation(oldCol: Int, oldRow: Int, newCol: Int, newRow: Int): Boolean = {
    println(oldCol + " " + oldRow + " " + newCol + " " + newRow)
    val to_x = newCol
    val to_y = newRow+1
    val fromX = oldCol
    var fromY = oldRow+1
    Player_turn=Playerrole
    if(validFrom(fromY,fromX))
    {
      if (validto(to_y,to_x,fromY,fromX)) {
         move(to_y,to_x,fromY,fromX)
        for(i<- 0 to 8)
        {
          for(j<- 0 to boardData(i).length-1)
          {
            print(" "+boardData(i)(j))
          }
          println()
        }
        if(!change_player)
          {
            if(Playerrole==1)
            {
              Playerrole=2
            }
            else
            {
              Playerrole=1
            }
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
        if (((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1) ||(to_y==fromY+1 && to_x==fromX+1) || (to_y==fromY+1 && to_x==fromX-1) ) && boardData(to_y)(to_x)!="x" ) {
          prompotion=true
          return true
        }
        else if((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2) || (to_y==fromY+2 && to_x==fromX+2) || (to_y==fromY+2 && to_x==fromX-2) )
        {
          if (boardData(fromY - 1)(fromX + 1) == "y" || boardData(fromY - 1)(fromX - 1) == "y") {
            eaten = true
            eat=true
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
        if (((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1)) && boardData(to_y)(to_x)!="y") {
          return true
        }
        else if ((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2)) {
          if (boardData(fromY - 1)(fromX + 1) == "y" || boardData(fromY - 1)(fromX - 1) == "y") {
            eaten = true
            eat=true
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
        if (((to_y == fromY - 1 && to_x == fromX + 1) || (to_y == fromY - 1 && to_x == fromX - 1) ||(to_y==fromY+1 && to_x==fromX+1) || (to_y==fromY+1 && to_x==fromX-1)) && boardData(to_y)(to_x)!="y"  ) {
          prompotion=true
          return true
        }
        else if((to_y == fromY - 2 && to_x == fromX + 2) || (to_y == fromY - 2 && to_x == fromX - 2) || (to_y==fromY+2 && to_x==fromX+2) || (to_y==fromY+2 && to_x==fromX-2) )
        {

          if (boardData(fromY - 1)(fromX + 1) == "x" || boardData(fromY - 1)(fromX - 1) == "x") {
            eaten = true
            eat=true
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
        if (((to_y == fromY + 1 && to_x == fromX + 1) || (to_y == fromY + 1 && to_x == fromX - 1)) && boardData(to_y)(to_x)!="x") {

          return true
        }
        else if ((to_y == fromY + 2 && to_x == fromX + 2) || (to_y == fromY + 2 && to_x == fromX - 2)) {
          if ((boardData(fromY + 1)(fromX + 1) == "x" )||( boardData(fromY + 1)(fromX - 1) == "x")) {
            eaten = true
            eat=true
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
    var Source:Circle=new Circle()
    val childrens = board.getChildren
     println(eaten)
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
          x_8=fromX-1
          y_8=fromY-2
          println("lol1")
          breakable {
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else
        {
          boardData(fromY-1)(fromX-1)="."
          x_8=fromX-1
          y_8=fromY-2
          println("lol2")
          breakable {
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
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
          println("lol3")
          boardData(fromY-1)(fromX+1)="-"
          x_8=fromX+1
          y_8=fromY-2
          breakable {
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else
        {
          println("lol4")
          println(boardData(4)(5))
          boardData(fromY-1)(fromX+1)="."
          println(boardData(4)(5))
          println(fromY-1)
          println(fromX+1)
          x_8=fromX+1
          y_8=fromY-2
          breakable {
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
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
          println("lol5")
          boardData(fromY+1)(fromX+1)="-"

          x_8=fromX+1
          y_8=fromY
          breakable {
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else
        {
          boardData(fromY+1)(fromX+1)="."
          x_8=fromX+1
          y_8=fromY
          breakable {
            println("lol6")
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }

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

          x_8=fromX-1
          y_8=fromY
          println("lol7")
          breakable {
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }
        }
        else
        {
          boardData(fromY+1)(fromX-1)="."
          x_8=fromX-1
          y_8=fromY
          println("lol8")
          breakable {
            childrens.forEach((node:Node)=>{
              if (node.isInstanceOf[Circle] && (GridPane.getRowIndex(node) == y_8) && (GridPane.getColumnIndex(node) == x_8)) {
                board.getChildren.remove(node)
                break
              }
            })
          }

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
      println("values")
      println(x_8)
      println(y_8)
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
