package Utils

import (
	"log"
	"os"
	"sync"
)

const (
	InfoLevel  = 1
	DebugLevel = 0
)

type AocLogger struct{ logLevel int }

var (
	instance *AocLogger
	once     sync.Once
)

func Logger() *AocLogger {
	once.Do(func() {
		instance = newLogger()
	})
	return instance
}

func newLogger() *AocLogger {
	var logLevel int

	value, exists := os.LookupEnv("LOG_LEVEL")
	if exists && value == "DEBUG" {
		logLevel = DebugLevel
	} else {
		logLevel = InfoLevel
	}

	return &AocLogger{logLevel: logLevel}
}

func (l *AocLogger) Info(format string, args ...any) {
	log.Printf(format, args...)
}

func (l *AocLogger) Debug(format string, args ...any) {
	if l.logDebugs() {
		log.Printf(format, args...)
	}
}

func (l *AocLogger) logDebugs() bool {
	return l.logLevel == DebugLevel
}
