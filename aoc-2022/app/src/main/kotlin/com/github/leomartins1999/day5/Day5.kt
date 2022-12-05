package com.github.leomartins1999.day5

import com.github.leomartins1999.Day
import java.util.ArrayDeque

class Day5 : Day {

    override fun part1(input: String) = parseInput(input)
        .moveCrates()
        .getTopCrates()

    override fun part2(input: String) = parseInput(input)
        .moveCrates(isCrateMover9001 = true)
        .getTopCrates()

    private fun parseInput(input: String): CargoCraneInput {
        val lines = input.split('\n')

        val separatorIdx = lines.indexOfFirst { it.isEmpty() }

        return CargoCraneInput(
            stacks = parseStacks(lines.subList(0, separatorIdx)),
            instructions = parseInstructions(lines.subList(separatorIdx + 1, lines.size))
        )
    }

    private fun parseStacks(lines: List<String>): List<ArrayDeque<Char>> {
        val containerLines = lines.subList(0, lines.size - 1)
        val stackIndexes = lines.last().getStackIndexes()

        return stackIndexes.map { idx ->
            val containers = containerLines
                .map { line -> line.getCharAtOrWhitespace(idx) }
                .filterNot { it.isWhitespace() }

            ArrayDeque(containers)
        }
    }

    private fun String.getStackIndexes() = splitBySpaces().map { indexOf(it) }

    private fun String.getCharAtOrWhitespace(idx: Int) =
        if (idx < length) this[idx]
        else ' '

    private fun parseInstructions(lines: List<String>) = lines
        .filter { it.isNotBlank() }
        .map { it.remove("move", "from", "to") }
        .map { it.toInstruction() }

    private fun String.remove(vararg strings: String) = strings.fold(this) { acc: String, s: String -> acc.replace(s, "") }

    private fun String.toInstruction() = splitBySpaces()
        .map { it.toInt() }
        .let {
            Instruction(
                crateCount = it[0],
                fromStack = it[1],
                toStack = it[2]
            )
        }

    private fun String.splitBySpaces() = split(" ").filter { it.isNotBlank() }

    private data class CargoCraneInput(
        val stacks: List<ArrayDeque<Char>>,
        val instructions: List<Instruction>
    ) {
        fun moveCrates(isCrateMover9001: Boolean = false) = apply {
            instructions.forEach {
                val fromStack = stacks[it.fromStack - 1]
                val toStack = stacks[it.toStack - 1]

                if (isCrateMover9001) pushMultiple(fromStack, toStack, it.crateCount)
                else pushOneByOne(fromStack, toStack, it.crateCount)
            }
        }

        private fun pushOneByOne(
            fromStack: ArrayDeque<Char>,
            toStack: ArrayDeque<Char>,
            count: Int
        ) = repeat(count) { toStack.push(fromStack.pop()) }

        private fun pushMultiple(
            fromStack: ArrayDeque<Char>,
            toStack: ArrayDeque<Char>,
            count: Int
        ) = (0 until count)
            .map { fromStack.pop() }
            .reversed()
            .forEach { toStack.push(it) }

        fun getTopCrates() = stacks
            .map { it.peek() }
            .joinToString(separator = "")
    }

    private data class Instruction(
        val crateCount: Int,
        val fromStack: Int,
        val toStack: Int
    )
}
