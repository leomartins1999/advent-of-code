/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.leomartins1999

import java.io.File

private const val BASE_PACKAGE = "com.github.leomartins1999"

fun main() {
    repeat(13) { runDay(it + 1) }
}

private fun runDay(number: Int) {
    val day = Class.forName("$BASE_PACKAGE.day$number.Day$number")
        .constructors
        .first()
        .newInstance() as Day

    val input = getFileText("./input/day-$number.txt")

    val part1 = day.part1(input)
    val part2 = day.part2(input)

    println("##### Day $number ######")
    println("Day $number (Part 1): \n$part1")
    println("Day $number (Part 2): \n$part2")
}

private fun getFileText(path: String) = File(path).readText()
