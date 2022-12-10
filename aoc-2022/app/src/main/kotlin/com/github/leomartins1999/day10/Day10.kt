package com.github.leomartins1999.day10

import com.github.leomartins1999.Day
import java.util.ArrayDeque

class Day10 : Day {

    override fun part1(input: String) = parseInput(input)
        .run()
        .first

    override fun part2(input: String) = parseInput(input)
        .run()
        .second
        .buildOutput()

    private fun parseInput(input: String) = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toInstruction() }
        .let { ArrayDeque(it) }

    private fun String.toInstruction() = split(" ")
        .filter { it.isNotBlank() }
        .let {
            Instruction(
                verb = it[0],
                value = if (it.size >= 2) it[1].toInt() else null
            )
        }

    private fun ArrayDeque<Instruction>.run(): Pair<Int, Set<Pair<Int, Int>>> {
        var register = 1
        var executing: Pair<Int, Int>? = null
        val samples = mutableMapOf<Int, Int>()
        val output = mutableSetOf<Pair<Int, Int>>()

        for (i in 1..240) {
            if (executing == null) {
                poll()?.let {
                    when (it.verb) {
                        "addx" -> executing = Pair(i + 1, it.value!!)
                    }
                }
            }

            // during
            if (i in samplingStops) {
                samples[i] = register
            }

            val position = (i - 1) % 40
            if (position in (register - 1..register + 1)) {
                output += Pair(position, (i - 1) / 40)
            }

            // after
            if (executing?.first == i) {
                register += executing!!.second
                executing = null
            }
        }

        val signalStrength = samples
            .map { it.value * it.key }
            .sum()

        return Pair(signalStrength, output)
    }

    private fun Set<Pair<Int, Int>>.buildOutput() =
        (0 until 6)
            .map { y ->
                (0 until 40)
                    .map { x ->
                        if (contains(Pair(x, y))) "#"
                        else "."
                    }
            }
            .map { it.joinToString(separator = "") }
            .joinToString(separator = "\n")

    private data class Instruction(
        val verb: String,
        val value: Int?
    )

    private companion object {
        val samplingStops = setOf(20, 60, 100, 140, 180, 220)
    }

}