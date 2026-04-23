package main

import (
	"fmt"
	"math"
)

type ErrNegativeSqrt float64

func (e ErrNegativeSqrt) Error() string {
	return fmt.Sprintf("cannot Sqrt negative number: %v", float64(e))
}

func Sqrt(x float64) (float64, error) {
	z := 1.0
	if x < 0 {
		return x, ErrNegativeSqrt(x)
	}
	for i := 0; i < 100; i++ {
		prev := z
		z -= (z*z - x) / (2*z)
		diff := math.Abs(prev - z)
		if diff < 1e-15 {
			return z, nil
		}
		//fmt.Println("z =", z, "diff =", (prev - z))
	}
	return z, nil
}

func main() {
	fmt.Println(Sqrt(2))
	fmt.Println(Sqrt(-2))
}
