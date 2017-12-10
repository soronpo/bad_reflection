object Test {
  type Cond[T] = T
  type MyCheckedType[T] = Checked[Cond, T]

  def myCheck[T](value : MyCheckedType[T]) : Unit = {} //also fails without type aliasing (value : Checked[Cond, T])

  myCheck(42)
}


//Workaround with inheritance
object BetterTest {
  type Cond[T] = T
  final class MyCheckedType[T](value : Int) extends BetterChecked[MyCheckedType, Cond, T](value)
  object MyCheckedType extends BetterCheckedCO[MyCheckedType, Cond]

  def myCheck[T](value : MyCheckedType[T]) : Unit = {} //also fails without type aliasing (value : Checked[Cond, T])

  myCheck(42)
}