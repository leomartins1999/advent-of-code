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
