package Day5_test

import (
	Day5 "aoc-2025/day5"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day5.Day5{InputFilePath: "./day-5-test.txt"}

	assert.Equal(t, 3, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day5.Day5{InputFilePath: "./day-5-test.txt"}

	assert.Equal(t, 14, day.SolvePart2())
}
