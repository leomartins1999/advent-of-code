package Day1

import (
	Utils "aoc-2025/utils"
	"bufio"
	"os"
	"strconv"
)

type Day1 struct{ InputFilePath string }

type Instruction struct {
	Direction string
	Times     int
}

type Dial struct {
	Location         int
	ExactZeroCounter int
	ZeroCounter      int
}

func (d *Day1) SolvePart1() any {
	instructions := parseInput(d.InputFilePath)
	dial := &Dial{Location: 50}

	for _, instruction := range instructions {
		dial.Move(instruction)
	}

	return dial.ExactZeroCounter
}

func (d *Day1) SolvePart2() any {
	instructions := parseInput(d.InputFilePath)
	dial := &Dial{Location: 50}

	for _, instruction := range instructions {
		dial.Move(instruction)
	}

	return dial.ZeroCounter
}

func (d *Dial) Move(instruction Instruction) {
	Utils.Logger().Debug("Moving %s %d from %d", instruction.Direction, instruction.Times, d.Location)

	switch instruction.Direction {
	case "L":
		d.Location -= instruction.Times
	case "R":
		d.Location += instruction.Times
	}

	for {
		if d.Location < 0 {
			d.Location += 100
			d.ZeroCounter++
		} else if d.Location >= 100 {
			d.Location -= 100
			d.ZeroCounter++
		} else {
			break
		}
	}

	if d.Location == 0 {
		d.ExactZeroCounter++
	}

	Utils.Logger().Debug("New location: %d", d.Location)
}

func parseInput(filePath string) []Instruction {
	file, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var instructions []Instruction
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		instructionText := scanner.Text()

		direction := instructionText[:1]
		times, err := strconv.Atoi(instructionText[1:])
		if err != nil {
			panic(err)
		}

		instructions = append(instructions, Instruction{Direction: direction, Times: times})
	}

	if err := scanner.Err(); err != nil {
		panic(err)
	}

	return instructions
}
