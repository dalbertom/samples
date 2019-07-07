package main

import "fmt"

type person struct {
	firstName string
	lastName  string
}

func main() {
	alex := person{"Alex", "Anderson"}
	alex2 := person{firstName: "Alex", lastName: "Anderson"}
	var alex3 person

	fmt.Println(alex)
	fmt.Println(alex2)
	fmt.Printf("%+v", alex3)
}
