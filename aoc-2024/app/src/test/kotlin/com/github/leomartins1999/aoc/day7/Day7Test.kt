package com.github.leomartins1999.aoc.day7

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    private val input = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

    @Test
    fun part1Example() {
        assertEquals(3749, Day7(input).part1())
    }
}
