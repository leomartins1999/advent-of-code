package com.github.leomartins1999.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Tests {

    private val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent()

    @Test
    fun part1(){
        val result = Day3().part1(input)

        assertEquals(157, result)
    }

    @Test
    fun part2(){
        val result = Day3().part2(input)

        assertEquals(70, result)
    }

}
