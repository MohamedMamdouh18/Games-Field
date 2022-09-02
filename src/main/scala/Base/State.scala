package Base

class State(oldR: Int, oldC: Int, newR: Int, newC: Int, t: Int) {
  var oldCol: Int = oldC
  var oldRow: Int = oldR
  var newCol: Int = newC
  var newRow: Int = newR
  var turn: Int = t
}
