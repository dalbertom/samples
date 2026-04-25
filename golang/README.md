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

#### Function values
tour/function-values.go
* Functions are values, too.
* Function values may be used as function arguments and return values.

##### Function closures
tour/function-closures.go
* A closure is a function value that references variables from outside its body.
* The function may access and assign to the referenced values; in this sense the function is "bound" to the variables.

##### Exercise: Fibonacci closure
tour/exercise-fibonacci-closure.go

## Methods and interfaces

### Methods
tour/methods.go
* Go does not have classes, but you can define methods on types
* A method is a function with a special _receiver_ argument.
* The receiver appears in its own argument list between the `func` keyword and the method name.

#### Methods are functions
tour/methods-funcs.go
* A method is just a function with a receiver argument.
* Here's `Abs` written as a regular function with no change in functionality.

#### Methods continued
tour/methods-continued.go
* You can declare a method on non-struct types, too

#### Pointer receivers
tour/methods-pointers.go
* You can declare methods with pointer receivers.
* This means the receiver type has the literal syntax `*T` for some type `T`. (Also, `T` cannot itself be a pointer such as `*int`.)
* Methods with pointer receivers can modify the value to which the receiver points.
* Pointer receivers are more common than value receivers since methods often need to modify their receiver.

#### Pointers and functions
tour/methods-pointsers-explained.go

#### Methods and pointer indirection
tour/indirection.go
* Functions with a pointer argument must take a pointer.
```
var v Vertex
ScaleFunc(v, 5) // Compile error!
ScaleFunc(&v, 5) // OK
```

* Methods with pointer receivers take either a value or a pointer as the receiver
```
var v Vertex
v.Scale(5) // OK
p := &v
p.Scale(10) // OK
```
* As a convenience, Go interprets the statement `v.Scale(5)` as `(&v).Scale(5)` since the `Scale` method has a pointer receiver.

tour/indirection-values.go
* The equivalent happens in the reverse direction.
* Functoins that take a value argument must take the value of that specific type:
```
var v Vertex
fmt.Println(AbsFunc(v)) // OK
fmt.Println(AbsFunc(&v)) // Compile error!
```

* while methods with value receivers take either a value or a pointer as the receiver when they are called:
```
var v Vertex
fmt.Println(v.Abs()) // OK
p := &v
fmt.Println(p.Abs()) // OK
```
* In this case, the method call `p.Abs()` is interpreted as `(*p).Abs()`

#### Choosing a value or pointer receiver
tour/methods-with-pointer-receivers.go
* There are two reasons to use a pointer receiver
  1. So that the method can modify the value that its receiver points to.
  2. To avoid copying the value on each method call. This can be more efficient if the receiver is a large struct.
* In this example, both `Scale` and `Abs` are methods with receiver type `*Vertex`, even though the `Abs` method does not need to modify its receiver.
* In general, all methods on a given type should have either value or pointer receivers, but not a mixture of both.

### Interfaces
tour/interfaces.go
* An _interface type_ is defined as a set of method signatures.
* A value of interface type can hold any value that implements those methods.

#### Interfaces are implemented implicitly
tour/interfaces-are-satisfied-implicitly.go
* A type implements an interface by implementing its methods.
* There is no explicit declaration of intent, no "implements" keywords.
* Implicit interfaces decouple the definition of an interface from its implementation, which could then appear in any package without prearrangement

#### Interface values
tour/interface-values.go
* Under the hood, interface values can be thought of as a tuple of a value and a concrete type:
```
(value, type)
```
* An interface value holds a value of a specific underlying concrete type.
* Calling a method on an interface value executes the method of the same name on its underlying type.

#### Interface values with nil underlying values
tour/interface-values-with-nil.go
* If the concrete value inside the interface itself is nil, the method will be called with a nil receiver.
* In some languages this would trigger a null pointer exception, but in Go it is common to write methods that gracefully handle being called with a nil receiver (as with the method `M` in the example).
* Note that an interface value that holds a nil concrete value is itself non-nil.

#### Nil interface values
tour/nil-interface-values.go
* A nil interface value holds neither value nor concrete type.
* Calling a method on a nil interface is a run-time error because there is no type inside the interface tuple to indicate which _concrete_ method to call.

#### The empty interface
tour/empty-interface.go
* The interface type that specifies zero methods is known as the _empty interface_:
```
interface{}
```
* An empty interface may hold values of any type. (Every type implements at least zero methods)
* Empty interfaces are used by code that handles values of unknown type. For example, `fmt.Print` takes any number of arguments of type `interface{}`.

### Type assertions
tour/type-assertions.go
* A _type assertion_ provides access to an interface value's underlying concrete value.
```
t := i.(T)
```
* This statement asserts that the interface value `i` holds the concrete type `T` and assigns the underlying `T` value to the variable `t`.
* If `i` does not hold a `T`, the statement will trigger a panic.

* To _test_ whether an interface value holds a specific type, a type assertion can return two values: the underlying value and a boolean value that reports whether the assertion succeeded.
```
t, ok := i.(T)
```
* If `i` holds a `T`, then `t` will be the underlying value and `ok` will be true.
* If not, `ok` will be false and `t` will be zer value of type `T`, and no panic occurs.
* Note the similarity between this syntax and that of reading from a map.

### Type switches
tour/type-switches.go
* A _type switch_ is a construct that permits several type assertions in series.
* A type switch is like a regular switch statement, but the cases in a type switch specify types (not values), and those values are compared against the type of the value held by the given interface value.
```
switch v := i.(type) {
case T:
    // here v has type T
case S:
    // here v has type S
default:
    // no match; here v has the same type as i
}
```
* The declaration of a type switch has the same syntax as a type assertion `i.(T)`, but the specific type `T` is replaced with the keyword `type`.
* This switch statement tests whether the interface value `i` holds a value of type `T` or `S`. In each of the `T` and `S` cases, the variable `v` will be of type `T` or `S` respectively and hold the value held by `i`. In the default case (where there is no match), the variable `v` is of the same interface type and value as `i`.

### Stringers
tour/stringer.go
* One of the most ubiquitous interfaces is `Stringer` defined in the `fmt` package.
```
type Stringer interface {
    String() string
}
```
* A `Stringer` is a type that can describe itself as a string. The `fmt` package (and many others) look for this interface to print values.

#### Exercise: Stringers
tour/exercise-stringer.go
* Make the `IPAddr` type implement `fmt.Stringer` to print the address as a dotted quad.
* For instance, `IPAddr{1, 2, 3, 4}` should print as `"1.2.3.4"`

### Errors
tour/errors.go
* Go programs express error state with `error` values.
* The `error` type is a built-in interface similar to `fmt.Stringer`:
```
type error interface {
    Error() string
}
```
* As with `fmt.Stringer`, the `fmt` package looks for the `error` interface when printing values.
* Functions often return an `error` value, and calling code should handle errors by testing whether the error equals `nil`.
```
i, err := strconv.Atoi("42")
if err != nil {
    fmt.Printf("couldn't convert number: %v\n", err)
    return
}
fmt.Println("Converted integer:", i)
```
* A nil `error` denotes success; a non-nil `error` denotes failure.

#### Exercise: Errors
tour/exercise-errors.go
* Copy your `Sqrt` function from the earlier exercise and modify it to return an `error` value.
* `Sqrt` should return a non-nil error value when given a negative number, as it doesn't support complex numbers.
* Create a new type:
```
type ErrNegativeSqrt float64
```
* and make it an `error` by giving it a
```
func (e ErrNegativeSqrt) Error() string
```
* method such that `ErrNegativeSqrt(-2).Error() returns `"cannot Sqrt negative number: -2"`.
* **Note:** a call to `fmt.Sprint(e)` inside the `Error` method will send the program into an infinite loop. You can avoid this by converting `e` first: `fmt.Sprint(float64(e))

### Readers
tour/reader.go
* The `io` package specifies the `io.Reader` interface, which represents the read end of a stream of data.
* The Go standard library contains [many implementations](https://cs.opensource.google/search?q=Read%5C(%5Cw%2B%5Cs%5C%5B%5C%5Dbyte%5C)&ss=go%2Fgo) of this interface, including files, network connections, compressors, ciphers, and others.
* The `io.Reader` interface has a `Read` method:
```
func (T) Read(b []byte) (n int, err error)
```
`Read` populates the given byte slice with data and returns the number of bytes populated and an error value. It returns an `io.EOF` error when the stream ends.

The example code creates a `strings.Reader` and consumes its output 8 bytes at a time.

#### Exercise: Readers
tour/exercise-reader.go
* Implement a `Reader` type that emits an infinite stream of the  ASCII character 'A'

#### Exercise: rot13Reader
tour/exercise-rot-reader.go
* A common pattern is an `io.Reader` that wraps another `io.Reader`, modifying the stream in some way.
* For example, the `gzip.NewReader` function takes an `io.Reader` (a stream of compressed data) and returns a `*gzip.Reader` that also implements `io.Reader` (a stream of decompressed data).
* Implement a `rot13Reader` that implements `io.Reader` and reads from an `io.Reader`, modifying the stream by applying the `rot13` substitution cipher to all alphabetical characters.
* The `rot13Reader` type is provided for you. Make it an `io.Reader` by implementing its `Read` method.

### Images
tour/images.go
[Package Image](https://go.dev/pkg/image/#Image) defines the `Image` interface:
```
package image
type Image interface {
    ColorModel() color.Model
    Bounds() Rectangle
    At(x, y int) color.Color
}
```

(See [the documentation](https://go.dev/pkg/image/#Image) for all the details.)

**Note:** the `Rectangle` return value of the `Bounds` method is actually an `image.Rectangle`, as the declaration is inside package `image`.
The `color.Color` and `color.Model` types are also interfaces, but we'll ignore that by using the predefined implementations `color.RGBA` and `color.RGBAModel`. These interfaces and types are specified by the [image/color package](https://go.dev/pkg/image/color/)

#### Exercise: Images
tour/exercise-images.go
* Similar to the previous picture generator, write another one, but this time it will return an implementation of `image.Image` instead of a slice of data.
* Define your own `Image` type, implement the necessary methods, and call `pic.ShowImage`.
* `Bounds` should return a `image.Rectangle`, like `iamge.Rect(0, 0, w, h)`.
* `ColorModel` should return `color.RGBAModel`.
* `At` should return a color; the value `v` in the last picture generator corresponds to `color.RGBA{v, v, 255, 255}` in this one.

## Generics

### Type parameters
tour/index.go
* Go functions can be written to work on multiple types using type parameters. The type parameters of a function appear between brackets, before the function's arguments.
```
func Index[T comparable](s []T, x T) int
```
* This declaration means that `s` is a slice of any type `T` that fulfills the built-in constraint `comparable`. `x` is also a value of the same type.
* `comparable` is a useful constraint that makes it possible to use the `==` and `!=` operators on values of the type.
* In this example, we use it to compare a value to all slice elements until a match is found. This `Index` function works for any type that supports comparison.

### Generic types
tour/list.go
* In addition to generic functions, Go also supports generic types. A type can be parameterized with a type parameter, which could be useful for implementing generic data structures.
* This example demonstrates a simple type declaration for a singly-linked list holding any type of value.
* As an exercise, add some functionality to this list implementation.

## Concurrency

### Goroutines
tour/goroutines.go
* A _goroutine_ is a lightweight thread managed by the Go runtime.
```
go f(x, y, z)
```
* starts a new goroutine running
```
f(x, y, z)
```
* The evaluation of `f`, `x`, `y`, and `z` happens in the current goroutine and the execution of `f` happens in the new goroutine.
* Goroutines run in the same address space, so access to shared memory must be synchronized.
* The `sync` package provides useful primitives, although you won't need them much in Go as there are other primitives.

---

### Concurrency
