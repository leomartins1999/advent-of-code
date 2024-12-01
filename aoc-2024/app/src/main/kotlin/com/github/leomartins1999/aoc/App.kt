package com.github.leomartins1999.aoc

fun main() {
    AoC2024().run()
}

class AoC2024 {
    fun run() {
        println("Welcome to AoC 2024! 🎉")

        val days = loadDays()
        days.forEachIndexed { index, day ->
            println("=".repeat(25))
            println("Day ${index + 1} (Part 1): \n${day.part1()}")
            println("Day ${index + 1} (Part 2): \n${day.part2()}")
        }
    }

    private fun loadDays(): List<Day> {

    }
}
