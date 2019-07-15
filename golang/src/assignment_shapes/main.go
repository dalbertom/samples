package main

import "fmt"

type triangle struct {
	base   float64
	height float64
}

type square struct {
	base float64
}

type shape interface {
	getArea() float64
}

func (s triangle) getArea() float64 {
	return 0.5 * s.base * s.height
}

func (s square) getArea() float64 {
	return s.base * s.base
}

func printArea(s shape) {
	fmt.Println("Area of", s, s.getArea())
}

func main() {
	t := triangle{5, 3}
	s := square{8}
	printArea(t)
	printArea(s)
}
