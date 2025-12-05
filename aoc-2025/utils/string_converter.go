package Utils

import "strconv"

func StringToInt(s string) int {
	n, err := strconv.Atoi(s)
	if err != nil {
		panic(err)
	}

	return n
}
