package Utils

import (
	"os"
	"strings"
)

func GetLinesFromFile(filePath string) []string {
	data, err := os.ReadFile(filePath)
	if err != nil {
		panic(err)
	}

	return strings.Split(string(data), "\n")
}

func GetMatrixFromFile(filePath string) [][]rune {
	lines := RemoveEmptyStrings(GetLinesFromFile(filePath))
	matrix := make([][]rune, len(lines))

	for i, line := range lines {
		matrix[i] = []rune(line)
	}

	return matrix
}
