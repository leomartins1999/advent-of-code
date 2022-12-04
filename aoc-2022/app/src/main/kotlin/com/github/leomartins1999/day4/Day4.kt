package com.github.leomartins1999.day4

import com.github.leomartins1999.Day

class Day4 : Day {

    override fun part1(input: String) = parseInput(input)
        .count { it.hasFullOverlap() }

    override fun part2(input: String) = parseInput(input)
        .count { it.hasOverlap() }

    private fun parseInput(input: String) = input
        .split('\n')
        .filter { it.isNotBlank() }
        .map { it.trim() }
        .map { it.toAssignmentPair() }

    private fun String.toAssignmentPair() = split(",")
        .map { it.toAssignment() }
        .let { AssignmentPair(it[0], it[1]) }

    private fun String.toAssignment() = split("-")
        .map { it.toInt() }
        .let { (it[0]..it[1]).toSet() }

    data class AssignmentPair(
        val first: Set<Int>,
        val second: Set<Int>
    ) {
        fun hasFullOverlap() = first.containsAll(second) or second.containsAll(first)
        fun hasOverlap() = first.containsAny(second) or second.containsAny(first)

        private fun Set<Int>.containsAny(other: Set<Int>) = any { other.contains(it) }
    }
}
