package com.github.leomartins1999.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Tests {

    private val input = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent()

    @Test
    fun part1() {
        val result = Day9().part1(input)

        assertEquals(13, result)
    }
}
