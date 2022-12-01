package com.github.leomartins1999.day1

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Tests {

    private val input = """
        1000
        2000
        3000

        4000
    
        5000
        6000

        7000
        8000
        9000

        10000
    """

    @Test
    fun part1() {
        val result = Day1().part1(input)

        assertEquals(24000, result)
    }

    @Test
    fun part2() {
        val result = Day1().part2(input)

        assertEquals(45000, result)
    }
}