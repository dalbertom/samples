package main

import (
	"fmt"
	"math"
)

func Sqrt(x float64) float64 {
	z := 1.0
	for i := 0; i < 100; i++ {
		prev := z
		z -= (z*z - x) / (2*z)
		diff := math.Abs(prev - z)
		if diff < 1e-15 {
			return z
		}
		fmt.Println("z =", z, "diff =", (prev - z))
	}
	return z
}

func main() {
	fmt.Println(Sqrt(2))
}
