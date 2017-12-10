object Test {
  type Cond[T] = T
  type MyCheckedType[T] = Checked[Cond, T]

  def myCheck[T](value : MyCheckedType[T]) : Unit = {} //also fails without type aliasing (value : Checked[Cond, T])

  myCheck(42)
}