package main

import "fmt"

// List represents a singly-linked list that holds
// values of any type.
type List[T any] struct {
	next *List[T]
	val T
}

func main() {
	intlist := List[int]{
		&List[int]{nil, 10},
		20,
	}

	fmt.Println(intlist.val, intlist.next.val)
}
