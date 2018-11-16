package main

import (
  "fmt"
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
}
