package Day8

import (
	Utils "aoc-2025/utils"
	"fmt"
	"math"
	"slices"
	"sort"
	"strings"
)

type Day8 struct {
	InputFilePath     string
	NrConnections     int
	NrLargestCircuits int
}

type JunctionBox struct{ x, y, z int }

type CandidatePair struct {
	box1, box2 JunctionBox
	distance   float64
}

type Circuit struct {
	boxes []JunctionBox
}

func (d *Day8) SolvePart1() any {
	boxes := parseInput(d.InputFilePath)
	Utils.Logger().Debug("Parsed %d junction boxes", len(boxes))

	circuits, _ := connect(boxes, d.NrConnections)

	largestCircuits := getLargestCircuits(circuits, d.NrLargestCircuits)

	sum := largestCircuits[0].size()
	for _, circuit := range largestCircuits[1:] {
		sum *= circuit.size()
	}

	return sum
}

func (d *Day8) SolvePart2() any {
	boxes := parseInput(d.InputFilePath)

	_, lastPair := connect(boxes, -1)

	return lastPair.box1.x * lastPair.box2.x
}

func (b *JunctionBox) distance(other JunctionBox) float64 {
	return math.Sqrt(math.Pow(float64(b.x)-float64(other.x), 2) + math.Pow(float64(b.y)-float64(other.y), 2) + math.Pow(float64(b.z)-float64(other.z), 2))
}

func (c *Circuit) size() int {
	return len(c.boxes)
}

func (c *Circuit) merge(other *Circuit) *Circuit {
	mergedBoxes := append(c.boxes, other.boxes...)
	return &Circuit{boxes: mergedBoxes}
}

func connect(boxes []JunctionBox, nrConnections int) ([]*Circuit, CandidatePair) {
	circuits := map[*Circuit]bool{}
	for _, box := range boxes {
		newCircuit := &Circuit{boxes: []JunctionBox{box}}
		circuits[newCircuit] = true
	}

	closestBoxPairs := getClosestBoxPairs(boxes, nrConnections)
	Utils.Logger().Debug("Closest box pairs: %v", closestBoxPairs)

	var lastPair CandidatePair
	for _, pair := range closestBoxPairs {
		Utils.Logger().Debug("Processing pair: %v", pair)
		lastPair = pair

		connectBoxes(circuits, pair.box1, pair.box2)

		if nrConnections == -1 && len(circuits) == 1 {
			Utils.Logger().Debug("All boxes connected into a single circuit")
			break
		}
	}

	return Utils.MapSetToSlice(circuits), lastPair
}

func connectBoxes(circuits map[*Circuit]bool, box1, box2 JunctionBox) {
	box1Found, box1Circuit := anyCircuitContainsBox(circuits, box1)
	box2Found, box2Circuit := anyCircuitContainsBox(circuits, box2)

	if box1Found && box2Found && (box1Circuit == box2Circuit) {
		Utils.Logger().Debug("Both boxes are already in the same circuit: %v", box1Circuit)
	} else if box1Found && box2Found {
		Utils.Logger().Debug("Merging circuits: %v and %v", box1Circuit, box2Circuit)
		superCircuit := box1Circuit.merge(box2Circuit)
		delete(circuits, box1Circuit)
		delete(circuits, box2Circuit)
		circuits[superCircuit] = true
	} else if box1Found {
		Utils.Logger().Debug("Adding box2 to box1's circuit: %v", box1Circuit)
		box1Circuit.boxes = append(box1Circuit.boxes, box2)
	} else if box2Found {
		Utils.Logger().Debug("Adding box1 to box2's circuit: %v", box2Circuit)
		box2Circuit.boxes = append(box2Circuit.boxes, box1)
	} else {
		Utils.Logger().Debug("Creating new circuit with boxes: %v and %v", box1, box2)
		newCircuit := &Circuit{boxes: []JunctionBox{box1, box2}}
		circuits[newCircuit] = true
	}
}

func getClosestBoxPairs(boxes []JunctionBox, nrConnections int) []CandidatePair {
	pairs := []CandidatePair{}

	for i, box1 := range boxes {
		for _, box2 := range boxes[i+1:] {
			distance := box1.distance(box2)

			pairs = append(pairs, CandidatePair{box1: box1, box2: box2, distance: distance})
		}
	}

	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i].distance < pairs[j].distance
	})

	if nrConnections == -1 {
		return pairs
	}

	return pairs[:nrConnections]
}

func anyCircuitContainsBox(circuits map[*Circuit]bool, box JunctionBox) (bool, *Circuit) {
	for circuit := range circuits {
		if slices.Contains(circuit.boxes, box) {
			return true, circuit
		}
	}

	return false, nil
}

func getLargestCircuits(circuits []*Circuit, nrLargest int) []*Circuit {
	sort.Slice(circuits, func(i, j int) bool {
		return circuits[i].size() > circuits[j].size()
	})

	return circuits[:nrLargest]
}

func parseInput(filePath string) []JunctionBox {
	lines := Utils.RemoveEmptyStrings(Utils.GetLinesFromFile(filePath))

	boxes := make([]JunctionBox, 0)
	for _, line := range lines {
		tokens := strings.Split(line, ",")

		if len(tokens) != 3 {
			panic(fmt.Sprintf("Invalid input line: %s", line))
		}

		x := Utils.StringToInt(tokens[0])
		y := Utils.StringToInt(tokens[1])
		z := Utils.StringToInt(tokens[2])

		boxes = append(boxes, JunctionBox{x: x, y: y, z: z})
	}

	return boxes
}
