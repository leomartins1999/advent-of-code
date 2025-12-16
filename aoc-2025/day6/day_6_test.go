package Day6_test

import (
	Day6 "aoc-2025/day6"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day6.Day6{InputFilePath: "./day-6-test.txt"}

	assert.Equal(t, 4277556, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day6.Day6{InputFilePath: "./day-6-test.txt"}

	assert.Equal(t, 3263827, day.SolvePart2())
}
