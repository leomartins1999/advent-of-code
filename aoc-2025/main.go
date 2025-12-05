package main

import (
	Day1 "aoc-2025/day1"
	Day2 "aoc-2025/day2"
	Day3 "aoc-2025/day3"
	Day4 "aoc-2025/day4"
	Day5 "aoc-2025/day5"
	Utils "aoc-2025/utils"
	"time"
)

type Day interface {
	SolvePart1() any
	SolvePart2() any
}

func main() {
	days := []Day{
		&Day1.Day1{InputFilePath: "./input/day-1.txt"},
		&Day2.Day2{InputFilePath: "./input/day-2.txt"},
		&Day3.Day3{InputFilePath: "./input/day-3.txt"},
		&Day4.Day4{InputFilePath: "./input/day-4.txt"},
		&Day5.Day5{InputFilePath: "./input/day-5.txt"},
	}

	Utils.Logger().Info("Advent of Code 2025 Solutions")
	for i, day := range days {
		part1Result, part1Time := doTimed(day.SolvePart1)
		Utils.Logger().Info("Day %d - Part 1: %v (Took %d ms)", i+1, part1Result, part1Time)

		part2Result, part2Time := doTimed(day.SolvePart2)
		Utils.Logger().Info("Day %d - Part 2: %v (Took %d ms)", i+1, part2Result, part2Time)
	}
}

func doTimed(f func() any) (any, int64) {
	start := time.Now()
	result := f()
	elapsed := time.Since(start).Milliseconds()
	return result, elapsed
}
