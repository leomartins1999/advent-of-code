package com.github.leomartins1999.day8

import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Tests {

    private val input = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent()

    @Test
    fun `part1`() {
        val result = Day8().part1(input)

        assertEquals(21, result)
    }

    @Test
    fun `part2`() {
        val result = Day8().part2(input)

        assertEquals(8, result)
    }

    @Test
    fun `getScenicScore for x=2,y=1`() {
        val result = Day8().getScenicScore(input, 2, 1)

        assertEquals(4, result)
    }

    @Test
    fun `getScenicScore for x=2,y=3`() {
        val result = Day8().getScenicScore(input, 2, 3)

        assertEquals(8, result)
    }

    @Test
    fun `getScenicScore for x=0,y=0`() {
        val result = Day8().getScenicScore(input, 2, 3)

        assertEquals(8, result)
    }
}
