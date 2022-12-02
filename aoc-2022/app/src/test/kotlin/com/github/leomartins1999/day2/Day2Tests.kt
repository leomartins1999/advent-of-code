package com.github.leomartins1999.day2

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Tests {

    private val input = """
        A Y
        B X
        C Z
    """.trimIndent()

    @Test
    fun part1(){
        val result = Day2().part1(input)

        assertEquals(15, result)
    }

    @Test
    fun part2(){
        val result = Day2().part2(input)

        assertEquals(12, result)
    }

}