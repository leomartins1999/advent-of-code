package com.github.leomartins1999.aoc.day3

import com.github.leomartins1999.aoc.Day

class Day3(private val input: String) : Day {

    // matches stuff like mul(integer, integer)
    // examples: mul(2,4), mul(15,23)
    private val regex = Regex("mul\\((?<first>\\d+),(?<second>\\d+)\\)")

    override fun part1(): Int {
        return regex.findAll(input).fold(0) { acc, matchResult ->
            acc + calc(matchResult)
        }
    }

    private fun calc(matchResult: MatchResult): Int {
        val first = matchResult.groups["first"]!!.value.toInt()
        val second = matchResult.groups["second"]!!.value.toInt()

        return first * second
    }
}
