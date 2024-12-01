package com.leomartins1999.aoc.day1

import kotlin.math.absoluteValue

fun main() {
    val input = Day1::class.java.getResource("/inputs/day1.txt")!!.readText()
    val part1Result = Day1(input).part1()

    println("Part 1: $part1Result")
}

class Day1(
    private val input: String
) {
    fun part1(): Int {
        val lists = parseInput()
        val comparisonResults = compareLists(lists)

        return comparisonResults.sum()
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
}
