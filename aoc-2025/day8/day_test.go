package Day8_test

import (
	Day8 "aoc-2025/day8"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day8.Day8{InputFilePath: "./day-test.txt", NrConnections: 10, NrLargestCircuits: 3}

	assert.Equal(t, 40, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day8.Day8{InputFilePath: "./day-test.txt", NrConnections: 10, NrLargestCircuits: 3}

	assert.Equal(t, 25272, day.SolvePart2())
}
