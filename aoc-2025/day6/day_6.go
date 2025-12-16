package Day6

import (
	Utils "aoc-2025/utils"
	"fmt"
	"strings"
)

type Day6 struct{ InputFilePath string }

type Problem struct {
	Values   []int
	Operator rune
}

func (d *Day6) SolvePart1() any {
	problems := parseInputNormally(d.InputFilePath)
	Utils.Logger().Debug("Parsed problems: %v", problems)

	res := 0
	for _, problem := range problems {
		res += problem.Solve()
	}

	return res
}

func (d *Day6) SolvePart2() any {
	problems := parseInputForCephalopods(d.InputFilePath)
	Utils.Logger().Debug("Parsed problems: %v", problems)

	res := 0
	for _, problem := range problems {
		res += problem.Solve()
	}

	return res
}

func (p Problem) Solve() int {
	res := p.Values[0]

	for _, val := range p.Values[1:] {
		switch p.Operator {
		case '+':
			res += val
		case '*':
			res *= val
		default:
			panic(fmt.Sprintf("Unknown operator: %c", p.Operator))
		}

	}

	return res
}

func parseInputNormally(filePath string) []Problem {
	lines := Utils.GetLinesFromFile(filePath)
	operators := []rune{}
	values := [][]int{}

	for _, line := range lines {
		tokens := Utils.RemoveEmptyStrings(strings.Split(line, " "))
		if len(tokens) == 0 {
			continue
		}

		if tokens[0] == "+" || tokens[0] == "*" {
			for _, token := range tokens {
				Utils.Logger().Debug("Parsing operator: %s", token)
				Utils.Logger().Debug("Current operators: %+v", operators)
				operators = append(operators, rune(token[0]))
			}
		} else {
			for idx, token := range tokens {
				Utils.Logger().Debug("Parsing token: %s", token)
				Utils.Logger().Debug("Current values: %+v", values)
				if len(values) <= idx {
					values = append(values, []int{Utils.StringToInt(token)})
				} else {
					values[idx] = append(values[idx], Utils.StringToInt(token))
				}
			}
		}
	}

	problems := []Problem{}
	for idx := range operators {
		problems = append(problems, Problem{
			Values:   values[idx],
			Operator: operators[idx],
		})
	}

	return problems
}

func parseInputForCephalopods(filePath string) []Problem {
	matrix := Utils.GetMatrixFromFile(filePath)
	subMatrixes := [][][]rune{}

	previousDividerIdx := 0
	for x := range matrix[0] {
		Utils.Logger().Debug("Parsing column %d", x)

		isDivider := true
		for y := range matrix {
			Utils.Logger().Debug("Checking row %d, column %d: %c", y, x, matrix[y][x])
			if matrix[y][x] != ' ' {
				Utils.Logger().Debug("Found non-space character at row %d, column %d: %c", y, x, matrix[y][x])
				isDivider = false
				break
			}
		}

		if isDivider {
			Utils.Logger().Debug("Found divider at column %d", x)
			subMatrixes = append(subMatrixes, extractSubMatrix(matrix, previousDividerIdx, x))
			previousDividerIdx = x
		}

		if x == len(matrix[0])-1 {
			Utils.Logger().Debug("Reached end of matrix at column %d", x)
			subMatrixes = append(subMatrixes, extractSubMatrix(matrix, previousDividerIdx, x+1))
		}
	}

	problems := []Problem{}
	for _, subMatrix := range subMatrixes {
		problems = append(problems, parseSubMatrix(subMatrix))
	}

	return problems
}

func extractSubMatrix(matrix [][]rune, startX, endX int) [][]rune {
	if startX != 0 {
		// if it's not the first sub-matrix, move one column to the right to skip the divider
		startX++
	}

	subMatrix := [][]rune{}
	for y := range matrix {
		row := []rune{}
		for x := startX; x < endX; x++ {
			row = append(row, matrix[y][x])
		}
		subMatrix = append(subMatrix, row)
	}

	return subMatrix
}

func parseSubMatrix(subMatrix [][]rune) Problem {
	values := []int{}

	Utils.Logger().Debug("Parsing sub-matrix: %+v", subMatrix)
	for x := range subMatrix[0] {
		value := 0
		for y := range subMatrix[:len(subMatrix)-1] {
			digit := subMatrix[y][x]
			Utils.Logger().Debug("Parsing digit at row %d, column %d: %c", y, x, digit)
			if digit == ' ' {
				continue
			}

			value = value*10 + Utils.DigitRuneToInt(digit)
		}

		values = append(values, value)
	}

	Utils.Logger().Debug("Parsed values: %+v", values)

	operatorLine := subMatrix[len(subMatrix)-1]
	Utils.Logger().Debug("Parsed operator line: %+v, using value %c", operatorLine, operatorLine[0])

	return Problem{
		Values:   values,
		Operator: operatorLine[0],
	}
}
