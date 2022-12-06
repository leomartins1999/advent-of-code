package com.github.leomartins1999.day6

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Tests {

    @Test
    fun `part1 - 1`() {
        val result = Day6().part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb")

        assertEquals(7, result)
    }

    @Test
    fun `part1 - 2`() {
        val result = Day6().part1("bvwbjplbgvbhsrlpgdmjqwftvncz")

        assertEquals(5, result)
    }

    @Test
    fun `part1 - 3`() {
        val result = Day6().part1("nppdvjthqldpwncqszvftbrmjlhg")

        assertEquals(6, result)
    }

    @Test
    fun `part1 - 4`() {
        val result = Day6().part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")

        assertEquals(10, result)
    }

    @Test
    fun `part1 - 5`() {
        val result = Day6().part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

        assertEquals(11, result)
    }

    @Test
    fun `part2 - 1`() {
        val result = Day6().part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb")

        assertEquals(19, result)
    }

    @Test
    fun `part2 - 2`() {
        val result = Day6().part2("bvwbjplbgvbhsrlpgdmjqwftvncz")

        assertEquals(23, result)
    }

    @Test
    fun `part2 - 3`() {
        val result = Day6().part2("nppdvjthqldpwncqszvftbrmjlhg")

        assertEquals(23, result)
    }

    @Test
    fun `part2 - 4`() {
        val result = Day6().part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")

        assertEquals(29, result)
    }

    @Test
    fun `part2 - 5`() {
        val result = Day6().part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

        assertEquals(26, result)
    }
}
