package Day4

import (
	Utils "aoc-2025/utils"
)

const (
	RollOfPapper = '@'
	Empty        = '.'
)

type Day4 struct{ InputFilePath string }

type Grid struct{ Cells [][]rune }

func (d *Day4) SolvePart1() any {
	grid := parseInput(d.InputFilePath)

	return len(grid.getAccessibleRollsOfPaper())
}

func (d *Day4) SolvePart2() any {
	grid := parseInput(d.InputFilePath)

	removedCnt := 0
	for {
		accessibleRollsOfPaper := grid.getAccessibleRollsOfPaper()
		if len(accessibleRollsOfPaper) == 0 {
			return removedCnt
		}

		grid.removeRollsOfPaper(accessibleRollsOfPaper)
		removedCnt += len(accessibleRollsOfPaper)
	}
}

func (g Grid) getAccessibleRollsOfPaper() [][2]int {
	accessibleRollsOfPaper := [][2]int{}

	for y := range g.Cells {
		for x := range g.Cells[y] {
			if g.isRollOfPaper(x, y) && g.isAccessible(x, y) {
				accessibleRollsOfPaper = append(accessibleRollsOfPaper, [2]int{x, y})
			}
		}
	}

	return accessibleRollsOfPaper
}

func (g Grid) isAccessible(x, y int) bool {
	adjacentCells := [][2]int{
		{x - 1, y - 1}, // Top-Left
		{x, y - 1},     // Top
		{x + 1, y - 1}, // Top-Right
		{x - 1, y},     // Left
		{x + 1, y},     // Right
		{x - 1, y + 1}, // Bottom-Left
		{x, y + 1},     // Bottom
		{x + 1, y + 1}, // Bottom-Right
	}

	adjacentCnt := 0
	for _, cell := range adjacentCells {
		if g.isValidPosition(cell[0], cell[1]) && g.isRollOfPaper(cell[0], cell[1]) {
			adjacentCnt++
		}
	}

	return adjacentCnt < 4
}

func (g *Grid) removeRollsOfPaper(positions [][2]int) {
	for _, pos := range positions {
		g.Cells[pos[1]][pos[0]] = Empty
	}
}

func (g Grid) isRollOfPaper(x, y int) bool {
	return g.Cells[y][x] == RollOfPapper
}

func (g Grid) isValidPosition(x, y int) bool {
	return x >= 0 && y >= 0 && y < len(g.Cells) && x < len(g.Cells[y])
}

func parseInput(filePath string) Grid {
	lines := Utils.GetLinesFromFile(filePath)

	grid := [][]rune{}
	for _, line := range lines {
		grid = append(grid, []rune(line))
	}

	return Grid{Cells: grid}
}
