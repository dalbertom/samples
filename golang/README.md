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

##### Switch with no condition
* It's the same as `switch true`.
* Can be a clean way to write long if-then-else chains
tour/switch-with-no-condition.go

#### Defer
* Defers the execution of a function until the surrounding function returns.
* The call's arguments are evaluated immediately, but the function call is not executed until the surrounding function returns.
tour/defer.go

##### Stacking defers
* Deferred function calls are pushed onto a stack.
* When a function returns, its deferred calls are executed in last-in-first-out order
tour/defer-multi.go

### More types: structs, slices, and maps

#### Pointers
tour/pointers.go
* The type `*T` is a pointer to a `T` value. Its zero value is `nil`.
* The `&` operator generates a pointer to its operand.
* The `*` operator denotes the pointer's underlying value. This is known as "dereferencing" or "indirecting"
* Unlike C, Go has no pointer arithmetic.

#### Structs
* It's a collection of fields.  tour/structs.go
* Struct fields are accessed using a dot. tour/struct-fields.go

##### Pointer to structs
* Struct fields can be accessed through a struct pointer.
* To access the field `X` of a struct when we have the struct pointer `p` we could write `(*p).X`. But that notation is cumbersome, so the language permits `p.X` without the explicit dereference.
tour/struct-pointers.go

##### Struct Literals
tour/struct-literals.go
* Denotes a newly allocated struct value by listing the values of its fields.
* You can list a subset of fields by using the `Name:` syntax (and the order of named fields is irrelevant).
* The special prefix `&` returns a pointer to the struct value.

#### Arrays
tour/array.go
* The type `[n]T` is an array of `n` values of type `T`.
* The expression `var a [10]int` declares a variable `a` as an array of ten integers.

#### Slices
tour/slices.go
* An array has a fixed size. A slice is a dynamically-sized, flexible view into the elements of an array.
* In practice, slices are much more common than arrays
* The type `[]T` is a slice with elements of type `T`
* A slice is formed by specifying two indices, a low and high bound, separated by a colon: `a[low : high]`

##### Slices are like references to arrays
tour/slice-pointers.go
* A slice does not store any data, it just describes a section of an underlying array.
* Changing the elements of a slice modifies the corresponding elements of its underlying array.
* Other slices that share the same underlying array will see those changes.

##### Slice literals
tour/slice-literals.go
* A slice literal is like an array literal without the length.
* This is an array literal: `[3]bool{true, true, false}`
* This creates the same array, then builds a slice that references it: `[]bool{true, true, false}`

##### Slice defaults
tour/slice-bounds.go
* When slicing, you may omit the high or low bounds to use their defaults instead.
* The default is zero for the low bound and the length of the slice for the high bound.

For the array `var a [10]int` these slice expressions are equivalent:
```
a[0:10]
a[:10]
a[0:]
a[:]
```

##### Slice length and capacity
tour/slice-len-cap.go
* A slice has both a _length_ and a _capacity_.
* The length is the number of elements it contains.
* The capacity is the number of elements in the underlying array, counting from the first element in the slice.
* The length and capacity of a slice `s` can be obtained via `len(s)` and `cap(s)`.
* You can extend a slice's length by re-slicing it, provided it has sufficient capacity.

##### Nil slices
tour/nil-slices.go
* The zero value of a slice is `nil`.
* A nil slice has a length and capacity of 0 and has no underlying array.

##### Creating a slice with make
tour/making-slices.go
* Slices can be created with the built-in `make` function; this is how you create dynamically-sized arrays.
* The `make` function allocates a zeroed array and returns a slice that refers to that array:
```
a := make([]int, 5) // len(a)=5
```
* To specify a capacity, pass a third argument to `make`:
```
b := make([]int, 0, 5) // len(b)=0, cap(b)=5
b = b[:cap(b)] // len(b)=5, cap(b)=5
b = b[1:] // len(b)=4, cap(b)=4
```

##### Slices of slices
tour/slices-of-slice.go
* Slices can contain any type, including other slices.

##### Appending to a slice
tour/append.go
* Go provides a built-in `append` function.
```
func append(s []T, vs ...T) []T
```
* First parameter is the slice, the rest are values to append to the slice.
* If the backing array is too small, a bigger one will be allocated.

##### Range
tour/range.go
* The `range` form of the `for` loop iterates over a slice or map.
* When ranging over a slice, two values are returned for each iteration. The first is the index, and the second is a copy of the element at that index.

* You can skip the index or value by assigning to `_`.
tour/range-continued.go
```
for i, _ := range pow
for _, value := range pow
```

If you only want the index, you can omit the second variable.
```
for i := range pow
```

##### Exercise: Slices
tour/exercise-slices.go

#### Maps
tour/maps.go
* The zero value of a map is `nil`. A `nil` map has no keys, nor can keys be added.
* The `make` function returns a map of the given type, initialized and ready for use.

##### Map literals
tour/map-literals.go
* They are like struct literals, but the keys are required.

##### Map literal
tour/map-literals-continued.go
* If the top-level type is just a type name, you can omit it from the elements of the literal.

##### Mutating Maps
tour/mutating-maps.go
* Insert or update an element in map `m`: `m[key] = elem`
* Retrieve an element: `elem = m[key]`
* Delete an element: `delete(m, key)`
* Test that a key is present with a two-value assignment: `elem, ok = m[key]`
* If `key` is in `m`, `ok` is `true`. If not, `ok` is `false`
* If `key` is not in the map, then `elem` is the zero value for the map's element type
* If `elem` or `ok` have not yet been declared you could use a short declaration form: `elem, ok := m[key]`

##### Exercise: Maps
tour/exercise-maps.go

---
## Methods and interfaces

### Methods and interfaces

## Generics

### Generics

## Concurrency

### Concurrency
