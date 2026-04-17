# Hello world
```
source .bashrc
cd src
go run hello.go
```

# Tour
https://go.dev/tour/list

tour/sandbox.go

## Basics

### Packages, variables, and functions

#### Packages
tour/packages.go

* Programs start running in package `main`
* The package name is the same as the last element of the import path, e.g. `import "math/rand"` comprises files that begin with the statement `package rand`

#### Imports
tour/imports.go

* It is good style to use factored import statement, e.g. instead of
```
import "fmt"
import "math"
```

we use

```go
import (
    "fmt"
    "math"
)
```

#### Exported names
tour/exported-names.go

* A name is exported if it begines with a capital letter, e.g. `Pi` is exported from the `math` package.
* When importing a package, you can refer only to its exported names.

#### Functions
tour/functions.go

* Can take zero or more arguments
* The type comes _after_ the variable, .e.g `func add(x int, y int) int { return x + y }`

* When two or more consecutive named function parameters share a type, you can omit the type from all but the last, e.g. `x int, y int` can be shortened to `x, y int`
tour/functions-continued.go

* A function can return any number of results, e.g. `func swap(x, y string) (string, string) { return y, x }`
tour/multiple-results.go

#### Named return values
tour/named-results.go

* Return values may be named, they are treated as variables defined at the top of the function.
* These names should be used to document the meaning of the return values.
* A `return` statement without arguments returns the named return values. This is known as the "naked" return.
* Naked return statements should be used only in short functions. They can harm readability in longer functions.

#### Variables

* A `var` statement can be at package or function level.
tour/variables.go

##### Variables with initializers
* A `var` declaration can include initializers, one per variable.
* If an initializer is present, the type can be omitted; the variable will take the type of the initializer.
tour/variables-with-initializers.go

##### Short variable declarations
tour/short-variable-declarations.go
* Inside a function, the `:=` short assignment statement can be used in place of a `var` declaration with implicit type.
* Outside of a function, every statement begins with a keyword (`var`, `func`, etc), so `:=` cannot be used.

#### Basic types

Types:
* bool
* string
* int int8 int16 int32 int64
  uint uint8 uint16 uint32 uint64 uintptr
* byte // alias for uint8
* rune // alias for int32
       // represents a Unicode code point
* float32 float64
* complex64 complex128

Notes:
* variable definitions may be "factored" into blocks, as with import statements
* The `int`, `uint`, and `uintptr` types are usually 32 bits wide on 32-bit systems and 64 bits wide on 64-bit systems.
* When you need an integer value you should use `int` unless you have a specific reason to use a sized or unsigned integer type.

##### Zero values
tour/zero.go
* Variables declared without an explicit initial value are given their _zero value_.
  * 0 for numeric types
  * false for boolean type
  * "" (empty string) for strings

##### Type conversions
tour/type-conversions.go
* The expression `T(v)` convers the value `v` to the type `T`.

Some numeric conversions:
```
var i int = 42
var f float64 = float64(i)
var u uint = uint(f)
```

Or, put more simply:
```
i := 42
f := float(i)
u := uint(f)
```

Unlike in C, in Go assignment between items of different type requires an explicit conversion.

##### Type inference
tour/type-inference.go
When declaring a variable without specifying an explicit type (e.g. using `:=` or `var =`) the variable's type is inferred from the value on the right hand side.
```
var i int
j := i // j is an int
```

But when the right hand side contains an untyped numeric constant, the new variable may be an `int`, `float64`, or `complex128` depending on the precision of the constant:
```
i := 42 // int
f := 3.142 // float64
g := 0.867 + 0.5i // complex128
```

#### Constants
tour/constants.go
* Declared like variables, but with the `const` keyword.
* Can be character, string, boolean, or numeric values.
* Cannot be declared using the `:=` syntax.

##### Numeric Constants
tour/numeric-constants.go
* Are high-precision _values_
* An untyped constants takes the type needed by its context

### Flow control statements: for, if, else, switch and defer

#### For

tour/for.go
* This is the only looping construct
* three components, separated by semicolons: init statement, condition expression, post statement

Note: unlike languages like C, Java or JavaScript, there are no parenthesis surrounding the three components of the `for` statement and the braces `{ }` are always required.

* The init and post statements are optional
tour/for-continued.go

* At that point you can drop the semicolons: C's `while` is spelled `for` in Go.
tour/for-is-gos-while.go

* If you omit the loop condition it loops forever
tour/forever.go

#### If
Similar to `for` in the sense that the expression need not be surrounded by parenthesis `( )` but the braces `{ }` are required.
tour/if.go

##### If with a short statement
tour/if-with-a-short-statement.go
* Like `for`, the `if` statement can start with a short statement to execute before the condition.
* Variables declared by the statement are only in scope until the end of the `if`.

##### If and else
* Variables declared inside an `if` short statement are also available inside any of the `else` blocks.
tour/if-and-else.go

##### Exercise: Loops and Functions
tour/exercise-loops-and-functions.go

#### Switch
* Similar to the one in C, C++, Java, JavaScript, PHP, except that Go does not have a fall-through.
* Also, cases don't need to be constants, and values don't need to be integers
tour/switch.go

##### Switch evaluation order
* They evaluate top to bottom, stopping when a case succeeds
tour/switch-evaluation-order.go

---

### More types: structs, slices, and maps

## Methods and interfaces

### Methods and interfaces

## Generics

### Generics

## Concurrency

### Concurrency
