// Add a method to metaclass
// http://stackoverflow.com/questions/4411815/add-method-to-metaclass

// Option 1. Wrap method in a closure
def option1(String s) {
  "The value is: $s"
}
String.metaClass.option1 = { -> option1(delegate) }
assert 'The value is: variable B' == 'variable B'.option1()

// Option 2: Using a category class and mixing it in
class PrintValueMethodCategory {
  static def option2(String s) {
    "The value is: $s"
  }
}
String.metaClass.mixin(PrintValueMethodCategory)
assert 'The value is: variable C' == 'variable C'.option2()
