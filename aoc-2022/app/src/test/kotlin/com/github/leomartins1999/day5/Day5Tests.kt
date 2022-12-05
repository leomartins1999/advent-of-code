package com.github.leomartins1999.day5

import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Tests {

    private val input = """
            [D]
        [N] [C]
        [Z] [M] [P]
         1   2   3

        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
    """.trimIndent()

    @Test
    fun part1() {
        val result = Day5().part1(input)

        assertEquals("CMZ", result)
    }

    @Test
    fun part2() {
        val result = Day5().part2(input)

        assertEquals("MCD", result)
    }
}
