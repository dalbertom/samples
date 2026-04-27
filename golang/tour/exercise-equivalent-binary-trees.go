package main

import "golang.org/x/tour/tree"
import "fmt"

func walk(t *tree.Tree, ch chan int, done <-chan struct{}) bool {
	if t == nil {
		return true
	}
	if !walk(t.Left, ch, done) {
		return false
	}
	select {
	case ch <- t.Value:
	case <-done:
		return false
	}
	return walk(t.Right, ch, done)
}

// Walk walks the tree t sending all values
// from the tree to the channel ch.
func Walk(t *tree.Tree, ch chan int) {
	walk(t, ch, nil)
}

// Same determines whether the trees
// t1 and t2 contain the same values.
func Same(t1, t2 *tree.Tree) bool {
	ch1 := make(chan int)
	ch2 := make(chan int)
	done := make(chan struct{})
	defer close(done)
	go func() {
		walk(t1, ch1, done)
		close(ch1)
	}()
	go func() {
		walk(t2, ch2, done)
		close(ch2)
	}()
	for {
		v1, ok1 := <-ch1
		v2, ok2 := <-ch2
		if !ok1 && !ok2 {
			return true
		} else if !ok1 && ok2 || ok1 && !ok2 {
			return false
		}
		if v1 != v2 {
			return false
		}
	}
	return true
}

func main() {
	fmt.Println(Same(tree.New(1), tree.New(1)))
	fmt.Println(Same(tree.New(1), tree.New(2)))
}
