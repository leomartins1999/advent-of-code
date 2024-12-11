package com.github.leomartins1999.aoc.day10

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    private val input = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

    @Test
    fun part1Example() {
        assertEquals(36, Day10(input).part1())
    }
}
