package main

import (
	"golang.org/x/tour/wc"
	"strings"
)

func WordCount(s string) map[string]int {
	counts := make(map[string]int)
	for _, w := range(strings.Fields(s)) {
		if _, ok := counts[w]; ok {
			counts[w] += 1
		} else {
			counts[w] = 1
		}
	}
	return counts
}

func main() {
	wc.Test(WordCount)
}
