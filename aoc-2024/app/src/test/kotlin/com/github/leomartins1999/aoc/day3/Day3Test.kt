package com.github.leomartins1999.aoc.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun part1Example() {
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

        assertEquals(161, Day3(input).part1())
    }

    @Test
    fun part2Example() {
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        assertEquals(48, Day3(input).part2())
    }
}
