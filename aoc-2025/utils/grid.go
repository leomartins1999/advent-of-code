package Utils

type Grid struct {
	Elements [][]rune
}

func (m *Grid) Find(target rune) (bool, int, int) {
	for y, row := range m.Elements {
		for x, val := range row {
			if val == target {
				return true, x, y
			}
		}
	}

	return false, -1, -1
}

func (m *Grid) Get(x, y int) rune {
	return m.Elements[y][x]
}

func (m *Grid) IsOutOfBounds(x, y int) bool {
	return y < 0 || y >= len(m.Elements) || x < 0 || x >= len(m.Elements[0])
}

func GridFromFile(filePath string) Grid {
	lines := RemoveEmptyStrings(GetLinesFromFile(filePath))
	grid := make([][]rune, len(lines))

	for i, line := range lines {
		grid[i] = []rune(line)
	}

	return Grid{Elements: grid}
}
