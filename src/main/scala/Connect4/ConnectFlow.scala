package Connect4

object ConnectFlow {

  var board: Array[Array[Int]] = Array.ofDim[Int](6, 7)
  var turn=1
  var gameOver=false
  def Gameflow() = {
    while(!gameOver){

      draw()
      var x = scala.io.StdIn.readInt()-1
      if(x>=0&&x<=6) {
        checking(x, turn)
        changeTurns()
      }
      else
        println("Please Enter a Valid Column")
    }
  }
  def checking(col:Int , color : Int ):Unit={
    var i =0
    var x=true
    while(x && i<6) {
      if(board(i)(col)==0)
        x=false
      else
        i+=1
    }
    if(i==6){
      println ("this colomn is full")
      changeTurns()
      return
    }
    putting (i,col,color)
    println(i)

  }

  def putting(row :Int, col:Int , color : Int):Unit= {
    board(row)(col)=color
  }

  def changeTurns():Unit={
    if (turn==1) turn =2 else turn=1
  }

  def draw():Unit={
    for(i<-5 to 0 by -1)
    {
      for(j<- 0 to 6)
      {
        print(" "+board(i)(j))
      }
      println()
    }
  }

}
