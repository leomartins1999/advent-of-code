package com.github.leomartins1999.aoc.day1

import com.github.leomartins1999.aoc.Day
import kotlin.math.absoluteValue

fun main() {
    val input = Day1::class.java.getResource("/inputs/day1.txt")!!.readText()
    val part1Result = Day1(input).part1()
    val part2Result = Day1(input).part2()

    println("Part 1: $part1Result")
    println("Part 2: $part2Result")
}

class Day1(
    private val input: String
): Day {
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

    private fun parseInput(): List<List<Int>> {
        val lines = input
            .split("\n")
            .filter { it.isNotBlank() }
            .map { parseLine(it) }

        val nrOfLists = lines.first().size

        return (0 until nrOfLists)
            .map { listIdx ->
                val elems = lines.map { it[listIdx] }

                elems.sorted()
            }
    }

    private fun parseLine(line: String): List<Int> {
        return line
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.toInt() }
    }

    private fun compareLists(lists: List<List<Int>>): List<Int> {
        val (first, second) = lists

        return first.zip(second) { firstValue, secondValue -> (firstValue - secondValue).absoluteValue }
    }

    private fun getSimilarityResults(lists: List<List<Int>>): List<Int> {
        val (first, second) = lists
        val secondOccurrenceMap = second.groupingBy { it }.eachCount()

        return first.map { value ->
            val nrOfOccurrences = secondOccurrenceMap[value] ?: 0

            value * nrOfOccurrences
        }
    }
}
