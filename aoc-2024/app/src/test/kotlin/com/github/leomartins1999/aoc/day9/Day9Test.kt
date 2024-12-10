package com.github.leomartins1999.aoc.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun part1Example() {
        val input = "2333133121414131402"

        assertEquals(1928, Day9(input).part1())
    }
}
