#!/usr/bin/env bash

# https://stackoverflow.com/a/2871034
set -euxo pipefail

echo "Running tests..."
./gradlew clean test

echo "Running linter..."
./gradlew ktlintFormat

echo "Done!"
