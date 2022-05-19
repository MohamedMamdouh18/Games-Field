package Connect4

import Connect4.CheckersController.{Frompiece, Player_turn, To_piece, board, eaten, fromPiece, promoted, prompotion}

class Checking_Moves {
  var change= new Convert_Char_Number()
  /// This function is used to validate the coordinate of the piece you want to move
  def validFrom():Boolean={
    val fromX= change.Convert_to_Char_Number(Frompiece(0))
    val fromY=change. Convert_to_Char_Number(Frompiece(1))

    if(Player_turn==1)
    {
      if(board(fromY+1)(fromX)=="x")
      {
        fromPiece = board(fromY+1)(fromX)
        return true
      }
    }else
    {
      if(board(fromY+1)(fromX)=="y")
      {
        fromPiece = board(fromY+1)(fromX)
        return true
      }
    }
    return false
  }
  /// This function is used to validate the coordinate of the place you want to move the piece to it
  def validto():Boolean={
    val to_x=change.Convert_to_Char_Number(To_piece(0))
    val to_y=change.Convert_to_Char_Number(To_piece(1))+1
    val fromX= change.Convert_to_Char_Number(Frompiece(0))
    val fromY= change.Convert_to_Char_Number(Frompiece(1))+1
    if(Player_turn==1)
    {
      if(board(to_y)(to_x)=="x")
      {
        return false
      }
    }
    else
    {
      if(board(to_y)(to_x)=="y")
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
          if (board(fromY - 1)(fromX + 1) == "y" || board(fromY - 1)(fromX - 1) == "y") {
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
          if (board(fromY - 1)(fromX + 1) == "y" || board(fromY - 1)(fromX - 1) == "y") {
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
          if (board(fromY - 1)(fromX + 1) == "x" || board(fromY - 1)(fromX - 1) == "x") {
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
          if (board(fromY + 1)(fromX + 1) == "x" || board(fromY - 1)(fromX + 1) == "x") {
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
  /// This function is used to validate the string entering by user
  def validSt(s: Array[Char]): Boolean = {
    if (s(0) >= 'A' && s(0) <= 'H') if (s(1) <= '8' && s(1) >= '1')
      {
        return true
      }
     return false
  }
}
