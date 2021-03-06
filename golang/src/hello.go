package main

import (
  "errors"
  "fmt"
  "math"
)

func main() {
  fmt.Println("Hello, world!")
  x := 5
  y := 7
  sum := x + y
  fmt.Println(sum)

  if x > 6 {
    fmt.Println("More than 6")
  } else if x < 2 {
    fmt.Println("Less than 2")
  } else {
    fmt.Println("Neither more than 6 nor less than 2")
  }

  var a [5]int
  a[2] = 7
  fmt.Println(a)
  b := [5]int{5, 4, 3, 2, 1}
  fmt.Println(b)
  // Slices are an abstraction on top of arrays
  // to make it easier to work with, e.g.
  // they do not have a fixed number of elements.
  c := []int{1, 2, 3, 4}
  c = append(c, 13)
  fmt.Println(c)

  vertices := make(map[string]int)
  vertices["triangle"] = 2
  vertices["square"] = 3
  vertices["dodecagon"] = 12
  delete(vertices, "square")
  fmt.Println(vertices)

  for i := 0; i < 5; i++ {
    fmt.Println(i)
  }

  i := 0
  for i < 5 { // there is no while loop, but this works
    fmt.Println(i)
    i++
  }

  arr := []string{"a", "b", "c"}
  for index, value := range arr {
    fmt.Println("index", index, "value", value)
  }
  m := make(map[string]string)
  m["a"] = "alpha"
  m["b"] = "beta"
  for key, value := range m {
    fmt.Println("key", key, "value", value)
  }

  result0 := sum0(2, 3)
  fmt.Println(result0)

  result, err := sqrt(9)
  if err != nil {
    fmt.Println(err)
  } else {
    fmt.Println(result)
  }

  p := person{name: "Jake", age: 23}
  fmt.Println(p)
  fmt.Println(p.age)

  j := 7
  inc(&j)
  fmt.Println(j)
  fmt.Println(&j)
}

func inc(x *int) {
  *x++
}

func sum0(x int, y int) int {
  return x + y
}

func sqrt(x float64) (float64, error) {
  if x < 0 {
    return 0, errors.New("Undefined for negative numbers")
  }

  return math.Sqrt(x), nil
}

type person struct {
  name string
  age int
}
