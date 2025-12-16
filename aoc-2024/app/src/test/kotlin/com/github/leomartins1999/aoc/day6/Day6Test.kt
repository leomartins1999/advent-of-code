package com.github.leomartins1999.aoc.day6

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    private val input =
        """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
        """.trimIndent()

    @Test
    fun part1Example() {
        assertEquals(41, Day6(input).part1())
    }

    @Test
    fun part2Example() {
        assertEquals(6, Day6(input).part2())
    }
}
