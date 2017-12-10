object Test {
  type Cond[T] = T
  type MyCheckedType[T] = Checked[Cond, T]

  def myCheck[T](value : MyCheckedType[T]) : Unit = {}

  myCheck(42)
}