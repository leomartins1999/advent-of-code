package main

import (
	Day1 "aoc-2025/day1"
	Day2 "aoc-2025/day2"
	"log"
)

type Day interface {
	SolvePart1() any
	SolvePart2() any
}

func main() {
	days := []Day{
		&Day1.Day1{InputFilePath: "./input/day-1.txt"},
		&Day2.Day2{InputFilePath: "./input/day-2.txt"},
	}

	log.Printf("Advent of Code 2025 Solutions")
	for i, day := range days {
		log.Printf("Day %d - Part 1: %v", i+1, day.SolvePart1())
		log.Printf("Day %d - Part 2: %v", i+1, day.SolvePart2())
	}
}
