package com.github.leomartins1999.aoc.day3

import com.github.leomartins1999.aoc.Day

class Day3(private val input: String) : Day {
    // matches stuff like mul(integer, integer), don't, do
    // examples: mul(2,4), mul(15,23), don't, do, mul(1,0)
    private val regex = Regex("mul\\((?<first>\\d+),(?<second>\\d+)\\)|don't|do")

    override fun part1() = scanCorruptedMemory()

    override fun part2() = scanCorruptedMemory(supportDoOps = true)

    private fun scanCorruptedMemory(supportDoOps: Boolean = false): Int {
        var multEnabled = true

        return regex
            .findAll(input)
            .fold(0) { acc, matchResult ->
                when (matchResult.value) {
                    "do" -> {
                        multEnabled = true
                        acc
                    }

                    "don't" -> {
                        multEnabled = false
                        acc
                    }

                    else -> if (!supportDoOps || multEnabled) acc + calcMult(matchResult) else acc
                }
            }
    }

    private fun calcMult(matchResult: MatchResult): Int {
        val first = matchResult.groups["first"]!!.value.toInt()
        val second = matchResult.groups["second"]!!.value.toInt()

        return first * second
    }
}
