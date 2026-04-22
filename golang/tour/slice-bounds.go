package main

import "fmt"

func main() {
	s := []int{2, 3, 5, 7, 11, 13}

	s = s[1:4]
	fmt.Println(s) // 3, 5, 7

	s = s[:2]
	fmt.Println(s) // 3, 5

	s = s[1:]
	fmt.Println(s) // 5
}
