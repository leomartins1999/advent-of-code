package Utils

import (
	"fmt"
	"os"
	"runtime/pprof"
	"time"
)

func DoTimed(f func() any) (any, int64) {
	start := time.Now()
	result := f()
	elapsed := time.Since(start).Milliseconds()
	return result, elapsed
}

func DoProfiled(f func() any) any {
	file, err := os.Create("run.prof")
	if err != nil {
		panic(fmt.Sprintf("could not create profile: %v", err))
	}
	defer file.Close()

	pprof.StartCPUProfile(file)
	defer pprof.StopCPUProfile()

	return f()
}
