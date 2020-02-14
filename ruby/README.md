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
* next
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

# Variables, Constants and Literals
## Global Variables
They begin with `$`. Uninitialized global variables have the value
`nil` and produce warnings with the `-w` option.
See [global-variables](global-variables)

## Instance Variables
They begin with `@`. Uninitialized instance variables have the value
`nil` and produce warnings with the -w option.
See [instance-variables](instance-variables)

## Ruby Class Variables
Begin with `@@` and must be initialized before they can be used in
method definitions.

Referencing an uninitialized class variable produces an error. Class
variables are shared among descendants of the class or module in which
the class variables are defined.

Overriding class variables produce warnings with the -w option.

See [class-variables](class-variables)

## Ruby Local Variables
Begin with a lowercase letter or `_`. Its scope ranges from `class`,
`module`, `def` or `do` to the corresponding `end` or from a block’s
opening brace to its close brace `{}`.

## Ruby Constants
Begin with an uppercase letter. The ones defined within a class or
module can be accessed from within that class or module. The ones
defined outside a class or module can be accessed globally.

Constants may not be defined within methods. Referencing an
uninitialized constant produces an error. Making an assignment
to a constant that is already initialized produces a warning.

See [constants](constants)

## Ruby Pseudo-Variables
* **self** the receiver object of the current method
* **true** value representing true
* **false** value representing false
* **nil** value representing undefined
* `__FILE__` the name of the current source file
* `__LINE__` the current line number in the source file

## Ruby Basic Literals
### Integer Numbers
Range from -2^30 to 2^30-1 or -2^62 to 2^62-1. Integers within this
range are objects of class `Fixnum` and integers outside this range
are stored in objects of class `Bignum`.

Base indicators: 0 for octal, 0x for hex, 0b for binary.

You can get the integer value corresponding to an ASCII character
or escape sequence by preceding it with a question mark.

```
123                 # Fixnum decimal
1_234               # Fixnum decimal with underline
-500                # Negative Fixnum
0377                # Octal
0xff                # Hexadecimal
0b1011              # Binary
?a                  # Character code for ‘a’
?\n                 # Code for newline
1234567891234567890 # Bignum
```

### Floating Numbers
Floating-point numbers are objects of class `Float` and can be any of
the following.

```
123.4   # Floating point value
1.0e6   # Scientific notation
4E20    # dot not required
4e+20   # sign before exponential
```

### String Literals
Sequences of 8-bit bytes and they are objects of class `String`.
Double-quoted strings allow substitution and backslash notation but
single-quoted strings don’t allow substitution and allow backslash
notation only for `\\` and `\’`

### Backslash Notations
| Notation | Character represented  |
| -------- | ---------------------  |
| \n       | Newline (0x0a)         |
| \r       | Carriage return (0x0d) |
| \f       | Formfeed (0x0c)        |
| \b       | Backspace (0x08)       |
| \a       | Bell (0x07)            |
| \e       | Escape (0x1b)          |
| \s       | Space (0x20)           |
| \nnn     | Octal notation         |
| \xnn     | Hexadecimal notation   |
| \cx,\C-x | Control-x              |
| \M-x     | Meta-x                 |
| \M-\C-x  | Meta-Control-x         |
| \x       | Character x            |

### Ruby Arrays
Comma-separated series of object references between square brackets. A
trailing comma is ignored.

See [arrays](arrays)

### Ruby Hashes
List of key/value pairs between braces, with either a comma or the
sequence `=>` between the key and the value. A trailing comma is
ignored.

See [hashes](hashes)

### Ruby Ranges
Ranges may be constructed using:
* `start..end` inclusive
* `start...end` exclusive
* `Range.new`
