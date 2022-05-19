package Connect4

class Convert_Char_Number {
  /// This function is used to convert char to Number
  def Convert_to_Char_Number( place:Char): Int ={
    if (place >= 'A' && place <= 'H')
    {
      place.toInt-65
    }
    else
    {
       place.toInt-49
    }
  }
}
