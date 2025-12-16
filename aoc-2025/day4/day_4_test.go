package Day4_test

import (
	Day4 "aoc-2025/day4"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day4.Day4{InputFilePath: "./day-4-test.txt"}

	assert.Equal(t, 13, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day4.Day4{InputFilePath: "./day-4-test.txt"}

	assert.Equal(t, 43, day.SolvePart2())
}
