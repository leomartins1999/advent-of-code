package Day7

import (
	Utils "aoc-2025/utils"
	"fmt"
)

type Day7 struct{ InputFilePath string }

type TachionManifold struct {
	Grid Utils.Grid
}

type Problem struct {
	Values   []int
	Operator rune
}

func (d *Day7) SolvePart1() any {
	tachion := parseInput(d.InputFilePath)

	return tachion.simulate()
}

func (d *Day7) SolvePart2() any {
	return "TODO"
}

func (t *TachionManifold) simulate() int {
	found, x, y := t.Grid.Find('S')
	if !found {
		panic("Start point 'S' not found in the grid")
	}

	return t.run(x, y)
}

// TODO: change to use set to deduplicate visited positions
func (t *TachionManifold) run(x, y int) int {
	Utils.Logger().Debug("At position (%d, %d)", x, y)

	if t.Grid.IsOutOfBounds(x, y) {
		return 0
	}

	nextCell := t.Grid.Get(x, y)
	switch nextCell {
	case 'S':
		return t.run(x, y+1)
	case '.':
		return 1 + t.run(x, y+1)
	case '^':
		return t.run(x-1, y) + t.run(x+1, y)
	default:
		panic(fmt.Sprintf("Encountered unhandled cell %s", string(nextCell)))
	}
}

func parseInput(filePath string) TachionManifold {
	grid := Utils.GridFromFile(filePath)

	return TachionManifold{Grid: grid}
}
