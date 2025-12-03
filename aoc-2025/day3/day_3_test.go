package Day3_test

import (
	Day3 "aoc-2025/day3"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day3.Day3{InputFilePath: "./day-3-test.txt"}

	assert.Equal(t, 357, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day3.Day3{InputFilePath: "./day-3-test.txt"}

	assert.Equal(t, 3121910778619, day.SolvePart2())
}
