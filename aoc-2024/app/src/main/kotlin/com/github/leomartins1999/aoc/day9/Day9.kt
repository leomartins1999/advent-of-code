package com.github.leomartins1999.aoc.day9

import com.github.leomartins1999.aoc.Day

class Day9(private val input: String) : Day {
    override fun part1(): Int {
        val diskMap = parseInput()

        return diskMap.compact().checksum()
    }

    private fun parseInput(): DiskMap {
        var isFreeSpace = false
        var idx = 0

        val blocks = input
            .toCharArray()
            .map(Character::getNumericValue)
            .map { length ->
                if (isFreeSpace) {
                    val blk = Block(length)
                    isFreeSpace = false
                    blk
                } else {
                    val blk = Block(length, MutableList(length) { idx })
                    isFreeSpace = true
                    idx++
                    blk
                }
            }

        return DiskMap(blocks)
    }
}

class DiskMap(private val blocks: List<Block>) {
    fun compact(): List<Block> {
        return listOf()
    }
}

fun List<Block>.checksum(): Int {
    return this
        .flatMap(Block::value)
        .mapIndexed { index, value -> index * value }
        .sum()
}

data class Block(val length: Int, val values: MutableList<Int> = mutableListOf()) {
    fun push(value: Int) = values.addLast(value)
    fun pop() = values.removeLast()
    fun isFull() = values.size == length
    fun value() = values
}
