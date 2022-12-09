package com.github.leomartins1999.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Tests {

    private val firstInput = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent()

    private val secondInput = """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
    """.trimIndent()

    @Test
    fun part1() {
        val result = Day9().part1(firstInput)

        assertEquals(13, result)
    }

    @Test
    fun `part2 - 1`() {
        val result = Day9().part2(firstInput)

        assertEquals(1, result)
    }

    @Test
    fun `part2 - 2`() {
        val result = Day9().part2(secondInput)

        assertEquals(36, result)
    }
}
