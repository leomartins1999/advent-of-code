package Day7

import (
	Utils "aoc-2025/utils"
	"fmt"
)

type Day7 struct{ InputFilePath string }

type TachionManifold struct {
	grid      Utils.Grid
	splitters map[string]bool
	visited   map[string]int
}

func (d *Day7) SolvePart1() any {
	tachion := parseInput(d.InputFilePath)
	tachion.simulate()

	return len(tachion.splitters)
}

func (d *Day7) SolvePart2() any {
	tachion := parseInput(d.InputFilePath)

	return tachion.simulate()
}

func (t *TachionManifold) simulate() int {
	found, x, y := t.grid.Find('S')
	if !found {
		panic("Start point 'S' not found in the grid")
	}

	return t.run(x, y)
}

func (t *TachionManifold) run(x, y int) int {
	key := fmt.Sprintf("(%d,%d)", x, y)

	if t.visited[key] != 0 {
		return t.visited[key]
	}

	if t.grid.IsOutOfBounds(x, y) {
		return 1
	}

	currentCell := t.grid.Get(x, y)
	var res int
	switch currentCell {
	case 'S':
		Utils.Logger().Debug("Starting point found at %s", key)
		res = t.run(x, y+1)
	case '.':
		Utils.Logger().Debug("Empty cell at %s", key)
		res = t.run(x, y+1)
	case '^':
		Utils.Logger().Debug("Splitter at %s", key)
		t.splitters[key] = true
		res = t.run(x-1, y) + t.run(x+1, y)
	default:
		panic(fmt.Sprintf("Encountered unhandled cell %s", string(currentCell)))
	}

	t.visited[key] = res
	return res
}

func parseInput(filePath string) TachionManifold {
	grid := Utils.GridFromFile(filePath)

	return TachionManifold{grid: grid, splitters: make(map[string]bool), visited: make(map[string]int)}
}
