package Utils

import "strconv"

func StringToInt(s string) int {
	n, err := strconv.Atoi(s)
	if err != nil {
		panic(err)
	}

	return n
}

func DigitRuneToInt(r rune) int {
	num := int(r - '0')
	if num < 0 || num > 9 {
		panic("rune is not a digit")
	}

	return num
}

func RemoveEmptyStrings(strings []string) []string {
	var result []string

	for _, str := range strings {
		if str != "" {
			result = append(result, str)
		}
	}

	return result
}
