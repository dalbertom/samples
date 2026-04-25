package main

import (
	"io"
	"os"
	"strings"
)

type rot13Reader struct {
	r io.Reader
}

func (r13 rot13Reader) Read(b []byte) (int, error) {
	b13 := make([]byte, len(b))
	n, err := r13.r.Read(b13)
	for i := 0; i < n; i++ {
		if b13[i] >= 'A' && b13[i] <= 'M' || b13[i] >= 'a' && b13[i] <= 'm' {
			b[i] = b13[i] + 13
		} else if b13[i] >= 'N' && b13[i] <= 'Z' || b13[i] >= 'n' && b13[i] <= 'z' {
			b[i] = b13[i] - 13
		} else {
			b[i] = b13[i]
		}
	}
	return n, err
}

func main() {
	s := strings.NewReader("Lbh penpxrq gur pbqr!")
	r := rot13Reader{s}
	io.Copy(os.Stdout, &r)
}
