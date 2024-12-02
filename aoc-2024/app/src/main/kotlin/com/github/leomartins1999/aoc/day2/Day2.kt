package com.github.leomartins1999.aoc.day2

import com.github.leomartins1999.aoc.Day
import kotlin.math.absoluteValue

class Day2(private val input: String) : Day {

    override fun part1(): Int {
        val reports = parseInput()

        return reports.count { it.isValid() }
    }

    override fun part2(): Int {
        val reports = parseInput()

        return reports.count { it.isValid(tolerateOneFailure = true) }
    }

    private fun parseInput(): List<Report> {
        return input
            .lines()
            .filter { it.isNotBlank() }
            .map { parseLine(it) }
    }

    private fun parseLine(line: String): Report {
        val levels = line
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.toInt() }

        return Report(levels)
    }
}

data class Report(val levels: List<Int>) {
    fun isValid(maxDifference: Int = 3, tolerateOneFailure: Boolean = false): Boolean {
        if (areLevelsValid(levels, maxDifference)) return true
        if (!tolerateOneFailure) return false

        levels.indices.forEach { idx ->
            val levelsCopy = levels.toMutableList()
            levelsCopy.removeAt(idx)

            if (areLevelsValid(levelsCopy, maxDifference)) return true
        }

        return false
    }

    private fun areLevelsValid(levels: List<Int>, maxDifference: Int): Boolean {
        val levelsCopy = levels.toMutableList()
        val first = levelsCopy.removeFirst()
        val second = levelsCopy.removeFirst()

        if (first == second || dif(first, second) > maxDifference) return false

        val descending = first > second

        var previous = second
        levelsCopy.forEach { current ->
            if (descending && current >= previous) return false
            if (!descending && current <= previous) return false
            if (dif(current, previous) > maxDifference) return false

            previous = current
        }

        return true
    }

    private fun dif(first: Int, second: Int) = (first - second).absoluteValue
}
