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
      if (condTpe.typeArgs.isEmpty) c.abort(c.enclosingPosition, "Cond should have type arguments. Bad Reflection!")
      q"new Checked[$condTpe, $tTpe](1)"
    }
  }
}
