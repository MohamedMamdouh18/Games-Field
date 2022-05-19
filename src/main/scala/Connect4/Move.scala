package Connect4

class Move {
  var change= new Convert_Char_Number()
  /// This function is used to move the pieces on the board if it is valid
  def move()={
    val to_x=change.Convert_to_Char_Number(To_piece(0))
    val to_y=change.Convert_to_Char_Number(To_piece(1))+1
    val fromX=change. Convert_to_Char_Number(Frompiece(0))
    val fromY=change. Convert_to_Char_Number(Frompiece(1))+1
    board(to_y)(to_x)=board(fromY)(fromX)

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
          board(fromY-1)(fromX-1)="-"
        }
        else
        {
          board(fromY-1)(fromX-1)="."
        }
        if(to_x!=0 && to_y!=0)
        {
          if(board(to_y-1)(to_x-1)=="y" && (board(to_y-2)(to_x-2)=="." || board(to_y-2)(to_x-2)=="-")  || board(to_y-1)(to_x+1)=="y" && (board(to_y-2)(to_x+2)=="." || board(to_y-2)(to_x+2)=="-"))
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
          if(board(to_y-1)(to_x+1)=="y" && (board(to_y-2)(to_x+2)=="." || board(to_y-2)(to_x+2)=="-"))
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
          board(fromY-1)(fromX+1)="-"
        }
        else
        {
          board(fromY-1)(fromX+1)="."
        }
        if(to_x!=0 && to_y!=0)
        {
          if(board(to_y-1)(to_x-1)=="y" && (board(to_y-2)(to_x-2)=="." || board(to_y-2)(to_x-2)=="-")  || board(to_y-1)(to_x+1)=="y" && (board(to_y-2)(to_x+2)=="." || board(to_y-2)(to_x+2)=="-"))
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
          if(board(to_y-1)(to_x+1)=="y" && (board(to_y-2)(to_x+2)=="." || board(to_y-2)(to_x+2)=="-"))
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
          board(fromY+1)(fromX+1)="-"
        }
        else
        {
          board(fromY+1)(fromX+1)="."
        }
        if(to_x!=0)
        {
          if((board(to_y+1)(to_x+1)=="y" && (board(to_y+2)(to_x+2)=="." || board(to_y+2)(to_x+2)=="-")) || board(to_y+1)(to_x-1)=="y" && (board(to_y+2)(to_x-2)=="." || board(to_y+2)(to_x-2)=="-") )
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
          if(board(to_y+1)(to_x+1)=="y" && (board(to_y+2)(to_x+2)=="." || board(to_y+2)(to_x+2)=="-"))
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
          board(fromY+1)(fromX-1)="-"
        }
        else
        {
          board(fromY+1)(fromX-1)="."
        }
        if(to_x!=0)
        {
          if((board(to_y+1)(to_x+1)=="y" && (board(to_y+2)(to_x+2)=="." || board(to_y+2)(to_x+2)=="-")) || board(to_y+1)(to_x-1)=="y" && (board(to_y+2)(to_x-2)=="." || board(to_y+2)(to_x-2)=="-") )
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
          if(board(to_y+1)(to_x+1)=="y" && (board(to_y+2)(to_x+2)=="." || board(to_y+2)(to_x+2)=="-"))
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
          if(board(to_y+1)(to_x-1)=="y" && (board(to_y+2)(to_x-2)=="." || board(to_y+2)(to_x-2)=="-") || board(to_y+1)(to_x+1)=="y" && (board(to_y+2)(to_x+2)=="." || board(to_y+2)(to_x+2)=="-") ||board(to_y-1)(to_x+1)=="y" && (board(to_y-2)(to_x+2)=="." || board(to_y-2)(to_x+2)=="-") || board(to_y-1)(to_x-1)=="y" && (board(to_y-2)(to_x-2)=="." || board(to_y-2)(to_x-2)=="-") )
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
      board(fromY)(fromX)="-"
    }
    else
    {
      board(fromY)(fromX)="."
    }

  }
}
