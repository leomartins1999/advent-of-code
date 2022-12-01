package com.github.leomartins1999.day1

class Day1 {

    fun part1(input: String): Int {
        val elfCalories = parseInput(input)

        return elfCalories.maxOf { it.sum() }
    }

    fun part2(input: String): Int {
        val elfCalories = parseInput(input)

        return elfCalories
            .map { it.sum() }
            .sortedDescending()
            .subList(0, 3)
            .sum()
    }

    private fun parseInput(input: String): List<List<Int>> {
        val elfCalories = mutableListOf<List<Int>>()

        val tokens = input
            .split("\n")
            .map { it.trim() }
            .map { it.toIntOrNull() }

        var current = mutableListOf<Int>()
        tokens.forEach {
            if (it != null) current += it
            else {
                elfCalories += current
                current = mutableListOf()
            }
        }

        return elfCalories
    }

}