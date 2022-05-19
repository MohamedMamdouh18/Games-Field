package Connect4

class DRAW {
  /// This Function is used to draw board of checkers
  def draw():Unit={
    for(i<- 0 to 8)
    {
      for(j<- 0 to board(i).length-1)
      {
        print(" "+board(i)(j))
      }
      println()
    }
  }
}
