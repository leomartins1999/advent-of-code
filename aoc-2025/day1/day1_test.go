package Day1_test

import (
	Day1 "aoc-2025/day1"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day1.Day1{InputFilePath: "./day-1-test.txt"}

	assert.Equal(t, 3, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day1.Day1{InputFilePath: "./day-1-test.txt"}

	assert.Equal(t, 6, day.SolvePart2())
}
