object MacroLand {
  import scala.reflect.macros.whitebox
  def apply() : Any = macro applyMacro
  def applyMacro(c: whitebox.Context)() : c.Tree = {
    import c.universe._
    new java.io.File(c.enclosingPosition.source.path).getParentFile.getCanonicalPath
    q"""
    object Foo {
      case class SomeClass() {
        val someField = 1
      }
    }
    Foo
    """
  }

}
