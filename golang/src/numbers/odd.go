package main

import "fmt"

func main() {
	numbers := []int{
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
	}
	for _, n := range numbers {
		if n%2 == 0 {
			fmt.Println(n, "is even")
		} else {
			fmt.Println(n, "is odd")
		}
	}
}
