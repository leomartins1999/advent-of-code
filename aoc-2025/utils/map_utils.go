package Utils

func MapSetToSlice[K comparable, V any](m map[K]V) []K {
	values := make([]K, 0, len(m))
	for k := range m {
		values = append(values, k)
	}
	return values
}
