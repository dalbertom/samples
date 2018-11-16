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
}
