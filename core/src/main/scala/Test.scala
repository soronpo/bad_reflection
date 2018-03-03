object Test {
//  val W = shapeless.Witness
//  val check : TwoFaceInt[W.`42`.T] = TwoFaceInt(42) //uses shapeless Witness to check type

  TwoFaceInt(1) + TwoFaceInt(2)
}