package com.github.leomartins1999.day13

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Tests {

    private val input = """
        [1,1,3,1,1]
        [1,1,5,1,1]

        [[1],[2,3,4]]
        [[1],4]

        [9]
        [[8,7,6]]

        [[4,4],4,4]
        [[4,4],4,4,4]

        [7,7,7,7]
        [7,7,7]

        []
        [3]

        [[[]]]
        [[]]

        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]
    """.trimIndent()

    @Test
    fun part1() {
        val result = Day13().part1(input)

        assertEquals(13, result)
    }

    @Test
    fun part2() {
        val result = Day13().part2(input)

        assertEquals(140, result)
    }
}
