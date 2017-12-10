import scala.reflect.macros.whitebox.Context

final class Checked[Cond[_], T](val value : Int)
object Checked {
  implicit def fromNumValue[Cond[_], T](value : T) : Checked[Cond, T] = macro Builder.Macro.fromNumValue[Cond, T]
}

object Builder {
  final class Macro(val c: Context) {
    def fromNumValue[Cond[_], T](value : c.Tree)(
      implicit
      cond : c.WeakTypeTag[Cond[_]], t : c.WeakTypeTag[T]
    ): c.Tree = {
      import c.universe._
      val condTpe = weakTypeOf[Cond[_]]
      val tTpe = weakTypeOf[T]
      print(s"Builder's Cond looks like: $condTpe")
      if (condTpe.typeArgs.isEmpty) c.abort(c.enclosingPosition, "Cond should have type arguments. Bad Reflection!")
      q"new Checked[$condTpe, $tTpe](1)"
    }
  }
}


class BetterChecked[Chk[_], Cond[_], T](val value : Int)
trait BetterCheckedCO[Chk[_], Cond[_]] { //to make companion object for BetterChecked
  implicit def fromNumValue[T](value : T) : Chk[T] = macro BetterBuilder.Macro.fromNumValue[Chk, Cond, T]
}

object BetterBuilder {
  final class Macro(val c: Context) {
    def fromNumValue[Chk[_], Cond[_], T](value : c.Tree)(
      implicit
      chk : c.WeakTypeTag[Chk[_]], cond : c.WeakTypeTag[Cond[_]], t : c.WeakTypeTag[T]
    ): c.Tree = {
      import c.universe._
      val chkSym = symbolOf[Chk[_]]
      val chkTpe = weakTypeOf[Chk[_]]
      val condTpe = weakTypeOf[Cond[_]]
      val tTpe = weakTypeOf[T]
      print(s"BetterBuilder's Cond looks like: $condTpe")
      if (condTpe.typeArgs.isEmpty) c.abort(c.enclosingPosition, "Cond should have type arguments. Bad Reflection!")
      q"new $chkSym[$tTpe](1)"
    }
  }
}
