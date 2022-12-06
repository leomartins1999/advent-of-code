package com.github.leomartins1999.day6

import com.github.leomartins1999.Day

class Day6 : Day {

    override fun part1(input: String) = findMarker(input, 4)
    override fun part2(input: String) = findMarker(input, 14)

    private fun findMarker(input: String, size: Int): Int {
        val chars = mutableListOf<Char>()

        input.forEachIndexed { idx, char ->
            if (chars.size == size) {
                if (chars.distinct().size == size) return idx

                chars.removeAt(0)
            }

            chars += char
        }

        return -1
    }
}
