package com.github.leomartins1999.day12

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Tests {

    private val input = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
    """.trimIndent()

    @Test
    fun part1() {
        val result = Day12().part1(input)

        assertEquals(31, result)
    }

    @Test
    fun part2() {
        val result = Day12().part2(input)

        assertEquals(29, result)
    }
}
