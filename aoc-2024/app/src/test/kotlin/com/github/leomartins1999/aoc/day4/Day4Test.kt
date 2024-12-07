package com.github.leomartins1999.aoc.day4

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day4Test {
    @Test
    fun part1Example() {
        val input = """
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

        assertEquals(18, Day4(input).part1())
    }
}
