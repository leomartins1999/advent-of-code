package com.github.leomartins1999.aoc.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    private val input = "2333133121414131402"

    @Test
    fun part1Example() {
        assertEquals(1928, Day9(input).part1())
    }

    @Test
    fun part2Example() {
        assertEquals(2858, Day9(input).part2())
    }
}
