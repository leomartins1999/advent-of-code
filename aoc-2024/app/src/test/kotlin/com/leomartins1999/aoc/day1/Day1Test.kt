package com.leomartins1999.aoc.day1

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    private val input = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

    @Test
    fun day1example() {
        assertEquals(11, Day1(input).part1())
    }
}
