package com.github.leomartins1999.day4

import kotlin.test.Test
import kotlin.test.assertEquals

class Day4Tests {

    private val input = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent()

    @Test
    fun part1() {
        val result = Day4().part1(input)

        assertEquals(2, result)
    }

    @Test
    fun part2() {
        val result = Day4().part2(input)

        assertEquals(4, result)
    }
}
