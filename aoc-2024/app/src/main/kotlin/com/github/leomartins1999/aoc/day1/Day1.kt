package com.github.leomartins1999.aoc.day1

import com.github.leomartins1999.aoc.Day
import kotlin.math.absoluteValue

class Day1(private val input: String) : Day {
    override fun part1(): Int {
        val lists = parseInput()
        val comparisonResults = compareLists(lists)

        return comparisonResults.sum()
    }

    override fun part2(): Int {
        val lists = parseInput()
        val similarityResults = getSimilarityResults(lists)

        return similarityResults.sum()
    }

    private fun parseInput(): Pair<List<Int>, List<Int>> {
        val lines =
            input
                .split("\n")
                .filter { it.isNotBlank() }
                .map { parseLine(it) }

        val (first, second) =
            listOf(0, 1)
                .map { listIdx ->
                    lines
                        .map { it[listIdx] }
                        .sorted()
                }

        return Pair(first, second)
    }

    private fun parseLine(line: String): List<Int> {
        return line
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.toInt() }
    }

    private fun compareLists(lists: Pair<List<Int>, List<Int>>): List<Int> {
        return lists.first.zip(lists.second) { firstValue, secondValue -> (firstValue - secondValue).absoluteValue }
    }

    private fun getSimilarityResults(lists: Pair<List<Int>, List<Int>>): List<Int> {
        val secondOccurrenceMap = lists.second.groupingBy { it }.eachCount()

        return lists.first.map { value ->
            val nrOfOccurrences = secondOccurrenceMap[value] ?: 0

            value * nrOfOccurrences
        }
    }
}
