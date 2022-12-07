package com.github.leomartins1999.day7

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Tests {

    private val input = """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """.trimIndent()

    @Test
    fun `part1`() {
        val result = Day7().part1(input)

        assertEquals(95437, result)
    }

    @Test
    fun `part2`() {
        val result = Day7().part2(input)

        assertEquals(24933642, result)
    }
}
