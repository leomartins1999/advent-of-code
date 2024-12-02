package com.github.leomartins1999.aoc.day2

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
    private val input =
        """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
        """.trimIndent()

    @Test
    fun part1Example() {
        assertEquals(2, Day2(input).part1())
    }

    @Test
    fun part1First2ElemsEqual() {
        val input =
            """
            1 1 2 3 4
            1 2 3 4
            """.trimIndent()

        assertEquals(1, Day2(input).part1())
    }

    @Test
    fun part1NewLineAtTheEnd() {
        val input =
            """
            1 2 3 4 5

            """.trimIndent()

        assertEquals(1, Day2(input).part1())
    }

    @Test
    fun part1BigDifferenceBetweenFirst2() {
        val input =
            """
            1 10 12 13 14
            """.trimIndent()

        assertEquals(0, Day2(input).part1())
    }

    @Test
    fun part1MoreInputs1() {
        val input =
            """
            65 68 71 72 71
            31 34 36 37 37
            80 83 84 86 87 90 92 96
            30 33 36 39 45
            21 22 25 23 24
            """.trimIndent()

        assertEquals(0, Day2(input).part1())
    }

    @Test
    fun part1MoreInputs2() {
        val input =
            """
            66 68 69 71 72 71 72 69
            2 3 5 4 4
            77 78 77 79 82 83 86 90
            6 9 10 7 9 12 17
            25 27 28 28 30 32
            """.trimIndent()

        assertEquals(0, Day2(input).part1())
    }

    @Test
    fun part1MoreInputs3() {
        val input =
            """
            61 63 66 68 68 66
            51 54 54 57 60 60
            50 52 52 53 56 60
            73 75 76 76 83
            19 20 24 26 28
            36 38 41 42 45 49 47
            56 59 63 64 64
            26 29 32 36 40
            """.trimIndent()

        assertEquals(0, Day2(input).part1())
    }

    @Test
    fun part1MoreInputs4() {
        val input =
            """
            70 72 74 75 77 80 84 89
            81 83 88 89 92 95 96
            79 80 85 87 89 92 93 90
            77 80 85 87 89 92 92
            29 31 33 38 40 42 46
            49 52 57 58 59 65
            14 11 14 17 18 19
            43 40 41 44 47 50 51 50
            31 30 32 35 35
            """.trimIndent()

        assertEquals(0, Day2(input).part1())
    }

    @Test
    fun part1MoreInputs5() {
        val input =
            """
            43 40 42 43 46 49 51 55
            9 7 9 12 15 17 18 23
            74 71 74 71 73 76 79 80
            50 49 50 49 52 50
            51 48 51 49 49
            23 22 23 25 22 26
            45 43 44 47 45 50
            42 39 41 43 43 45 48
            97 94 96 98 98 99 97
            """.trimIndent()

        assertEquals(0, Day2(input).part1())
    }

    @Test
    fun part2Example() {
        assertEquals(4, Day2(input).part2())
    }
}
