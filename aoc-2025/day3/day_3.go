package Day3

import (
	Utils "aoc-2025/utils"
	"bufio"
	"os"
	"strconv"
)

type Day3 struct{ InputFilePath string }

type BatteryBank struct{ JoltageValues []int }

func (d *Day3) SolvePart1() any {
	batteryBanks := parseInput(d.InputFilePath)

	sum := 0
	for _, bank := range batteryBanks {
		largestValue := bank.largestJoltage(2)
		Utils.Logger().Debug("Largest joltage pair in bank %v is %d", bank.JoltageValues, largestValue)
		sum += largestValue
	}

	return sum
}

func (d *Day3) SolvePart2() any {
	batteryBanks := parseInput(d.InputFilePath)

	sum := 0
	for _, bank := range batteryBanks {
		largestValue := bank.largestJoltage(12)
		Utils.Logger().Debug("Largest joltage 12th set in bank %v is %d", bank.JoltageValues, largestValue)
		sum += largestValue
	}

	return sum
}

func (b BatteryBank) largestJoltage(n int) int {
	batteries := b.JoltageValues
	sum := 0

	for batteriesEnabled := range n {
		Utils.Logger().Debug("Current sum: %d", sum)
		Utils.Logger().Debug("Batteries enabled: %d", batteriesEnabled)
		Utils.Logger().Debug("Remaining batteries: %v", batteries)

		lastIndexToConsider := len(batteries) - (n - batteriesEnabled) + 1
		toSearch := batteries[:lastIndexToConsider]

		Utils.Logger().Debug("Searching max in: %v", toSearch)

		value, index := findMax(toSearch)

		Utils.Logger().Debug("Found max value %d at index %d", value, index)

		sum *= 10
		sum += value

		batteries = batteries[index+1:]
	}

	return sum
}

func findMax(values []int) (int, int) {
	max := values[0]
	maxIndex := 0

	for i, v := range values {
		if v > max {
			max = v
			maxIndex = i
		}
	}

	return max, maxIndex
}

func parseInput(filePath string) []BatteryBank {
	file, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var batteryBanks []BatteryBank
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()

		joltageValues := []int{}
		for _, char := range line {
			value, err := strconv.Atoi(string(char))
			if err != nil {
				panic(err)
			}

			joltageValues = append(joltageValues, value)
		}

		batteryBanks = append(batteryBanks, BatteryBank{JoltageValues: joltageValues})
	}

	if err := scanner.Err(); err != nil {
		panic(err)
	}

	return batteryBanks
}
