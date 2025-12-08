package Day7_test

import (
	Day7 "aoc-2025/day7"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day7.Day7{InputFilePath: "./day-7-test.txt"}

	assert.Equal(t, 21, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day7.Day7{InputFilePath: "./day-7-test.txt"}

	assert.Equal(t, 40, day.SolvePart2())
}
