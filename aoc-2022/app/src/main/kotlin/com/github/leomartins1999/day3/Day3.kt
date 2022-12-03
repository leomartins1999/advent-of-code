package com.github.leomartins1999.day3

import com.github.leomartins1999.Day

class Day3 : Day {

    override fun part1(input: String) = parseInput(input).sumOf { it.getRucksackPriority() }

    override fun part2(input: String) = parseInput(input)
        .chunked(3)
        .sumOf { it.getBadgePriority() }

    private fun parseInput(input: String) = input
        .split("\n")
        .filter { it.isNotBlank() }

    private fun String.getRucksackPriority(): Int {
        val (first, second) = getRucksacks()

        val common = (first intersect second).first()

        return common.getPriority()
    }

    private fun String.getRucksacks(): Pair<Set<Char>, Set<Char>> {
        val tokens = chunked(length / 2)

        val first = tokens[0]
        val second = tokens[1]

        return Pair(first.toRucksack(), second.toRucksack())
    }

    private fun String.toRucksack() = toCharArray().toSet()

    private fun Char.getPriority() =
        if (this in 'b'..'z') this - 'a' + 1
        else this - 'A' + 27

    private fun List<String>.getBadgePriority() =
        map { it.toRucksack() }
            .reduce { acc, chars -> acc intersect chars }
            .first()
            .getPriority()
}
