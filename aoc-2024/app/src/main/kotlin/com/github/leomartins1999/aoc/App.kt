package com.github.leomartins1999.aoc

fun main() {
    AoC2024().run()
}

class AoC2024 {
    fun run() {
        println("Welcome to AoC 2024! 🎉")

        val days = loadDays()
        days.forEach { day ->
            println("=".repeat(25))

            val runSlowParts = System.getenv("RUN_SLOW")?.toBoolean() ?: false

            val slowParts = day.slowParts()
            val part1Result = if (slowParts.contains(1) && !runSlowParts) "⚠️🐢️ SKIPPED 🐢⚠️" else day.part1()
            val part2Result = if (slowParts.contains(2) && !runSlowParts) "⚠️🐢️ SKIPPED 🐢⚠️" else day.part2()

            println("${day::class.simpleName} (Part 1): $part1Result")
            println("${day::class.simpleName} (Part 2): $part2Result")
        }
    }

    private fun loadDays(): List<Day> {
        val days = mutableListOf<Day>()

        (1..25).forEach { dayNumber ->
            val day = instanceDay(dayNumber) ?: return days
            days += day
        }

        return days
    }

    private fun instanceDay(dayNumber: Int): Day? {
        val klass = getClassFor(dayNumber) ?: return null
        val input = this::class.java.getResource("/inputs/day$dayNumber.txt")!!.readText()

        return klass
            .constructors
            .first()
            .newInstance(input) as Day
    }

    private fun getClassFor(dayNumber: Int): Class<Day>? {
        return try {
            Class.forName("${basePackageName()}.day$dayNumber.Day$dayNumber") as Class<Day>
        } catch (e: ClassNotFoundException) {
            null
        }
    }

    private fun basePackageName() = this::class.java.packageName
}
