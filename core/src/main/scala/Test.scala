object Test extends App {
  val m = MacroLand()
  import m.S //Try complete me here
  import m.SomeClass //error highlighting is OK, but no completions are offered
}