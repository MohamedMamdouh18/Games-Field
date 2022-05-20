package GameEngines.GamesControllers

import Connect4.ConnectFlow.draw
import javafx.scene.layout.GridPane
import javafx.scene.shape.Circle

class Connect4Controller extends Controller {

  var boardControl: Array[Array[Int]] = Array.ofDim[Int](6, 7)
  var turn=1

  override def Draggable(source: Circle): Unit = {
    checking(GridPane.getColumnIndex(source),turn)
    changeTurns()
    draw()

  }

  def checking(col:Int , color : Int ):Unit={
    var i =0
    var x=true
    while(x && i<6) {
      if(boardControl(i)(col)==0)
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
    boardControl(row)(col)=color
  }

  def changeTurns():Unit={
    if (turn==1) turn =2 else turn=1
  }
  def draw():Unit={
    for(i<-5 to 0 by -1)
    {
      for(j<- 0 to 6)
      {
        print(" "+boardControl(i)(j))
      }
      println()
    }
  }

}
