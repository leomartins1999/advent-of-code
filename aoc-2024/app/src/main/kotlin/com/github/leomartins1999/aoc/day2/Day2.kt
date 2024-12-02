package com.github.leomartins1999.aoc.day2

import com.github.leomartins1999.aoc.Day
import kotlin.math.absoluteValue

class Day2(private val input: String) : Day {

    override fun part1(): Int {
        val reports = parseInput()

        return reports.count { it.isValid() }
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
    fun isValid(): Boolean {
        val levelsCopy = levels.toMutableList()
        val first = levelsCopy.removeFirst()
        val second = levelsCopy.removeFirst()
        if (first == second || (first - second).absoluteValue > 3) return false

        val descending = first > second

        var previous = second
        levelsCopy.forEach { current ->
            if (descending && current >= previous) return false
            if (!descending && current <= previous) return false

            val dif = (current - previous).absoluteValue
            if (dif > 3) return false

            previous = current
        }

        return true
    }
}
