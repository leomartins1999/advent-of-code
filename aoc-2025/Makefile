install:
	go mod tidy

test:
	LOG_LEVEL=DEBUG go test ./...

run:
	go run .

prof:
	go run . && go tool pprof -http=":8080" run.prof

build:
	go build .

format:
	go fmt ./... && go vet ./...
