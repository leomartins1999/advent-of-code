package Day5

import (
	Utils "aoc-2025/utils"
	"slices"
	"strings"
)

type Day5 struct{ InputFilePath string }

type IngredientIDRange struct{ Start, End int }

func (d *Day5) SolvePart1() any {
	ranges, ids := parseInput(d.InputFilePath)

	Utils.Logger().Debug("Parsed Ranges: %+v", ranges)
	Utils.Logger().Debug("Parsed IDs: %+v", ids)

	cnt := 0
	for _, id := range ids {
		if isFreshIngredient(id, ranges) {
			cnt++
		}
	}

	return cnt
}

func (d *Day5) SolvePart2() any {
	ranges, _ := parseInput(d.InputFilePath)

	reducedRanges := reduceRanges(ranges)

	return countIngredientsInRanges(reducedRanges)
}

func (r IngredientIDRange) OverlapsWith(other IngredientIDRange) bool {
	return r.Start <= other.End && other.Start <= r.End
}

func (r IngredientIDRange) MergeWith(other IngredientIDRange) IngredientIDRange {
	return IngredientIDRange{
		Start: min(r.Start, other.Start),
		End:   max(r.End, other.End),
	}
}

func reduceRanges(ranges []IngredientIDRange) []IngredientIDRange {
	result := []IngredientIDRange{}

	for _, r := range ranges {
		merged := false

		for idx, mergedRange := range result {
			if r.OverlapsWith(mergedRange) {
				newMergedRange := r.MergeWith(mergedRange)
				Utils.Logger().Debug("Merging range %+v with %+v to %+v", r, mergedRange, newMergedRange)
				result[idx] = newMergedRange
				merged = true
				break
			}
		}

		if !merged {
			Utils.Logger().Debug("Adding new range %+v", r)
			result = append(result, r)
		}
	}

	return result
}

func countIngredientsInRanges(ranges []IngredientIDRange) int {
	total := 0

	for _, r := range ranges {
		nIngredients := r.End - r.Start + 1
		Utils.Logger().Debug("Range %+v has %d ingredients", r, nIngredients)
		total += nIngredients
	}

	return total
}

func isFreshIngredient(id int, ranges []IngredientIDRange) bool {
	for _, r := range ranges {
		if id >= r.Start && id <= r.End {
			return true
		}
	}

	return false
}

func parseInput(filePath string) ([]IngredientIDRange, []int) {
	lines := Utils.GetLinesFromFile(filePath)

	ranges := []IngredientIDRange{}
	ids := []int{}

	for _, line := range lines {
		if line == "" {
			continue
		} else if strings.Contains(line, "-") {
			tokens := strings.Split(line, "-")
			start := Utils.StringToInt(tokens[0])
			end := Utils.StringToInt(tokens[1])

			ranges = append(ranges, IngredientIDRange{Start: start, End: end})
		} else {
			ids = append(ids, Utils.StringToInt(line))
		}
	}

	// Sorting ranges by its start value ensures merging works correctly otherwise merge order
	// can lead to unexpected overlapping ranges
	slices.SortFunc(ranges, func(r1, r2 IngredientIDRange) int {
		return r1.Start - r2.Start
	})

	return ranges, ids
}
