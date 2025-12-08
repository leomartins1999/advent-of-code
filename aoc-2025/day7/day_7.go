package Day7

import (
	Utils "aoc-2025/utils"
	"fmt"
)

type Day7 struct{ InputFilePath string }

type TachionManifold struct {
	grid      Utils.Grid
	splitters map[string]bool
	visited   map[string]bool
}

const (
	OutOfBounds = "out-of-bounds"
	Noop        = "noop"
)

func (d *Day7) SolvePart1() any {
	tachion := parseInput(d.InputFilePath)
	tachion.simulate()

	return len(tachion.splitters)
}

func (d *Day7) SolvePart2() any {
	return "TODO"
}

func (t *TachionManifold) simulate() {
	found, x, y := t.grid.Find('S')
	if !found {
		panic("Start point 'S' not found in the grid")
	}

	t.run(x, y)
}

func (t *TachionManifold) run(x, y int) {
	key := fmt.Sprintf("(%d,%d)", x, y)

	if t.visited[key] {
		return
	}

	t.visited[key] = true

	if t.grid.IsOutOfBounds(x, y) {
		return
	}

	currentCell := t.grid.Get(x, y)
	switch currentCell {
	case 'S':
		Utils.Logger().Debug("Starting point found at %s", key)
		t.run(x, y+1)
	case '.':
		Utils.Logger().Debug("Empty cell at %s", key)
		t.run(x, y+1)
	case '^':
		Utils.Logger().Debug("Splitter at %s", key)
		t.splitters[key] = true
		t.run(x-1, y)
		t.run(x+1, y)
	default:
		panic(fmt.Sprintf("Encountered unhandled cell %s", string(currentCell)))
	}
}
func parseInput(filePath string) TachionManifold {
	grid := Utils.GridFromFile(filePath)

	return TachionManifold{grid: grid, splitters: make(map[string]bool), visited: make(map[string]bool)}
}
