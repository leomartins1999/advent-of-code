package Day2

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

type Day2 struct{ InputFilePath string }

type IDRange struct {
	Start int
	End   int
}

func (d *Day2) SolvePart1() any {
	ranges := parseInput(d.InputFilePath)

	invalidIDs := []int{}
	for _, r := range ranges {
		invalidIDs = append(invalidIDs, r.getMatchingIDs(isMirrored)...)
	}

	sum := 0
	for _, id := range invalidIDs {
		sum += id
	}

	return sum
}

func (d *Day2) SolvePart2() any {
	ranges := parseInput(d.InputFilePath)

	invalidIDs := []int{}
	for _, r := range ranges {
		invalidIDs = append(invalidIDs, r.getMatchingIDs(isRepeated)...)
	}

	sum := 0
	for _, id := range invalidIDs {
		sum += id
	}

	return sum
}

func (r IDRange) getMatchingIDs(f func(int) bool) []int {
	invalidIDs := []int{}
	for i := r.Start; i <= r.End; i++ {
		if f(i) {
			invalidIDs = append(invalidIDs, i)
		}
	}

	// log.Printf("Found %v matching IDs in range %d-%d", invalidIDs, r.Start, r.End)

	return invalidIDs
}

func parseInput(filePath string) []IDRange {
	file, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var ranges []IDRange
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		rangesText := scanner.Text()
		parts := strings.Split(rangesText, ",")

		for _, rText := range parts {
			rParts := strings.Split(rText, "-")
			start, err := strconv.Atoi(rParts[0])
			if err != nil {
				panic(err)
			}

			end, err := strconv.Atoi(rParts[1])
			if err != nil {
				panic(err)
			}

			ranges = append(ranges, IDRange{Start: start, End: end})
		}

	}

	if err := scanner.Err(); err != nil {
		panic(err)
	}

	return ranges
}

func isMirrored(id int) bool {
	idStr := strconv.Itoa(id)

	if len(idStr)%2 == 1 {
		return false
	}

	firstHalf := idStr[:len(idStr)/2]
	secondHalf := idStr[len(idStr)/2:]

	return firstHalf == secondHalf
}

func isRepeated(id int) bool {
	idStr := strconv.Itoa(id)

	for chunkSize := 1; chunkSize <= len(idStr)/2; chunkSize++ {
		if isChunkRepeated(idStr, chunkSize) {
			return true
		}
	}

	return false
}

func isChunkRepeated(id string, chunkSize int) bool {
	chunk := id[:chunkSize]

	for i := chunkSize; i < len(id); i += chunkSize {
		end := i + chunkSize
		if end > len(id) {
			end = len(id)
		}

		if id[i:end] != chunk {
			return false
		}
	}

	return true
}
