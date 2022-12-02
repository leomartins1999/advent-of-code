/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.leomartins1999

import com.github.leomartins1999.day1.Day1
import com.github.leomartins1999.day2.Day2
import java.io.File

fun main() {
    day1()
    day2()
}

private fun day1(){
    val day = Day1()
    val dayInput = getFileText("./input/day-1.txt")

    val part1 = day.part1(dayInput)
    val part2 = day.part2(dayInput)

    println("#".repeat(25))
    println("Day 1 (Part 1): $part1")
    println("Day 1 (Part 2): $part2")
}

private fun day2(){
    val day = Day2()
    val dayInput = getFileText("./input/day-2.txt")

    val part1 = day.part1(dayInput)
    val part2 = day.part2(dayInput)

    println("#".repeat(25))
    println("Day 2 (Part 1): $part1")
    println("Day 2 (Part 2): $part2")
}

private fun getFileText(path: String) = File(path).readText()
