package com.github.leomartins1999.aoc.day4

import kotlin.test.Test
import kotlin.test.assertEquals

class Day4Test {
    private val input =
        """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
        """.trimIndent()

    @Test
    fun part1Example() {
        assertEquals(18, Day4(input).part1())
    }

    @Test
    fun part2Example() {
        assertEquals(9, Day4(input).part2())
    }
}
