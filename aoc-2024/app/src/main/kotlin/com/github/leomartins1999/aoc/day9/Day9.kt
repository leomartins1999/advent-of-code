package com.github.leomartins1999.aoc.day9

import com.github.leomartins1999.aoc.Day

class Day9(private val input: String) : Day {
    override fun part1(): Long {
        val diskMap = parseInput()

        diskMap.compact()

        return diskMap.checksum()
    }

    override fun part2(): Long {
        val diskMap = parseInput()

        diskMap.compactKeepingFiles()

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

        while (startPtr < endPtr) {
            val startBlock = blocks[startPtr]
            val endBlock = blocks[endPtr]

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

    fun compactKeepingFiles() {
//        println(inspect())

        (blocks.size - 1 downTo 0).forEach endPtrLoop@ { endPtr ->
            val endBlock = blocks[endPtr]

            (0 until endPtr).forEach { startPtr ->
                if(endBlock.isEmpty()) return@endPtrLoop

                val startBlock = blocks[startPtr]

                if(endBlock.fitsIn(startBlock)) {
                    while(!endBlock.isEmpty()) {
                        startBlock.push(endBlock.pop())
                    }

//                    println(inspect())
                }
            }
        }
    }

    fun checksum(): Long {
        return blocks
            .flatMap(Block::value)
            .mapIndexed { index, value -> index.toLong() * value }
            .sum()
    }

    fun inspect(): String {
        return blocks.map(Block::inspect).joinToString("")
    }
 }

data class Block(val length: Int, val values: MutableList<Int> = mutableListOf()) {
    fun push(value: Int) = values.addLast(value)
    fun pop() = values.removeLast()
    fun isFull() = values.size == length
    fun isEmpty() = values.isEmpty()
    fun freeSpace() = length - values.size
    fun value(): List<Int> {
        val vals = values.toMutableList()

        repeat(freeSpace()) { vals.add(0) }

        return vals
    }

    fun fitsIn(other: Block) = length <= other.freeSpace()

    fun inspect(): String {
        val str = values.joinToString("")

        return str + ".".repeat(freeSpace())
    }
}
