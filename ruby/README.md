http://www.tutorialspoint.com/ruby/

# Syntax
## Reserved words
* BEGIN
* END
* alias
* and
* begin
* break
* case
* class
* def
* defined?
* do
* else
* elsif
* ensure
* false
* for
* if
* in
* module
* enxt
* nil
* not
* or
* redo
* rescue
* retry
* return
* self
* super
* then
* true
* undef
* unless
* until
* when
* while
* `__FILE__`
* `__LINE__`

## BEGIN and END statements
used to declare code to be called at the beginning or end of the program.

## Comments
* Comments are lines that start with `#` or inlined with `#`.
* Block comments can be enclosed by `=begin` and `=end` lines.

# Classes

## Defining a class
Starts with the keyword `class` followed by the name of the class.
The name should always be in initial capitals.

## Variables in a Ruby Class
* **Local Variables:** they begin with a lowercase letter or `_`.
* **Instance Variables:** they are preceded by the at sign `@` followed by
the variable name.
* **Class Variables:** (aka static variables) They are preceded by `@@`
followed by the variable name.
* **Global Variables:** They are preceded by `$`.

## Creating objects using `new` method
The `initialize` method is executed when the `new` method of the class
is called with parameters.

## Member functions in Ruby Class
Functions are called methods. Each method starts with the keyword
`def` followed by the method name. The method name is always preferred
in **lowercase letters**. You end a method with the keyword `end`.
