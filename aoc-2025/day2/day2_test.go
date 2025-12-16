package Day2_test

import (
	Day2 "aoc-2025/day2"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_SolvePart1(t *testing.T) {
	day := &Day2.Day2{InputFilePath: "./day-2-test.txt"}

	assert.Equal(t, 1227775554, day.SolvePart1())
}

func Test_SolvePart2(t *testing.T) {
	day := &Day2.Day2{InputFilePath: "./day-2-test.txt"}

	assert.Equal(t, 4174379265, day.SolvePart2())
}
