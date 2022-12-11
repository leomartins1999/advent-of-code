package com.github.leomartins1999.day11

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Tests {

    private val input = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
    """.trimIndent()

    @Test
    fun part1() {
        val result = Day11().part1(input)

        assertEquals(10605, result)
    }

    @Test
    fun part2() {
        val result = Day11().part2(input)

        assertEquals(2713310158, result)
    }
}
