package com.github.leomartins1999.aoc.day9

import com.github.leomartins1999.aoc.Day

class Day9(private val input: String) : Day {
    override fun part1(): Long {
        val diskMap = parseInput()

        diskMap.compact()

        return diskMap.checksum()
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
    fun compact() {
        var startPtr = 0
        var endPtr = blocks.size - 1

        while (true) {
            val startBlock = blocks[startPtr]
            val endBlock = blocks[endPtr]

            if (startPtr >= endPtr) break

            if (startBlock.isFull()) {
                startPtr++
                continue
            }
            if (endBlock.isEmpty()) {
                endPtr--
                continue
            }

            val toMove = mutableListOf<Int>()
            while (toMove.size < startBlock.freeSpace() && !endBlock.isEmpty()) {
                toMove.add(endBlock.pop())
            }

            toMove.forEach(startBlock::push)
        }
    }

    fun checksum(): Long {
        return blocks
            .flatMap(Block::value)
            .mapIndexed { index, value -> index.toLong() * value }
            .sum()
    }
}

data class Block(val length: Int, val values: MutableList<Int> = mutableListOf()) {
    fun push(value: Int) = values.addLast(value)
    fun pop() = values.removeLast()
    fun isFull() = values.size == length
    fun isEmpty() = values.isEmpty()
    fun freeSpace() = length - values.size
    fun value() = values
}
