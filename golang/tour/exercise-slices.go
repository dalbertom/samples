package main

// go mod init tour
// go mod tidy
// go run exercise-slices.go
import "golang.org/x/tour/pic"

func Pic(dx, dy int) [][]uint8 {
	pic := make([][]uint8, dy)
	for i := 0; i < len(pic); i++ {
		pic[i] = make([]uint8, dx)
	}
	for x := 0; x < dx; x++ {
		for y := 0; y < dy; y++ {
			pic[y][x] = uint8((x+y)/2)
		}
	}
	return pic
}

func main() {
	pic.Show(Pic)
}
