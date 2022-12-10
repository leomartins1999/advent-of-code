package com.github.leomartins1999.day10

import com.github.leomartins1999.Day
import java.util.ArrayDeque

private typealias InstructionStack = ArrayDeque<Instruction>
private typealias ClockTick = Int
private typealias RegisterValue = Int
private typealias SignalStrengthSample = Pair<ClockTick, RegisterValue>

private typealias OnTick = (ClockTick, RegisterValue) -> Unit

private data class Instruction(
    val verb: String,
    val value: Int?
)

private data class InProgressInstruction(
    val completionClock: ClockTick,
    val onComplete: () -> Unit
)

class Day10 : Day {

    override fun part1(input: String): Int {
        val instructions = parseInput(input)

        val samples = mutableListOf<SignalStrengthSample>()
        val samplingStops = setOf(20, 60, 100, 140, 180, 220)

        instructions.run { clock, register ->
            if (clock in samplingStops) samples += SignalStrengthSample(clock, register)
        }

        return samples.sumOf { (clock, register) -> clock * register }
    }

    override fun part2(input: String): String {
        data class Pixel(val x: Int, val y: Int)

        val instructions = parseInput(input)

        val litPixels = mutableListOf<Pixel>()
        val (panelWidth, panelHeight) = Pair(40, 6)
        val panel = MutableList(panelHeight) { MutableList(panelWidth) { "." } }

        instructions.run { clock, register ->
            val x = (clock - 1) % panelWidth
            val y = (clock - 1) / panelWidth

            if (x in register - 1..register + 1) litPixels += Pixel(x, y)
        }

        litPixels.forEach { (x, y) -> panel[y][x] = "#" }

        return panel.joinToString(separator = "\n") { it.joinToString(separator = "") }
    }

    private fun parseInput(input: String) = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toInstruction() }
        .let { InstructionStack(it) }

    private fun String.toInstruction() = split(" ")
        .filter { it.isNotBlank() }
        .let {
            Instruction(
                verb = it[0],
                value = if (it.size >= 2) it[1].toInt() else null
            )
        }

    private fun InstructionStack.run(onTick: OnTick = { _, _ -> }) {
        var register = 1
        var inProgress: InProgressInstruction? = null

        for (clock in 1..240) {
            if (inProgress == null) {
                val instruction = poll()

                when {
                    instruction == null -> {}
                    instruction.verb == "noop" -> {}
                    instruction.verb == "addx" -> {
                        inProgress = InProgressInstruction(clock + 1) { register += instruction.value!! }
                    }
                }
            }

            // during
            onTick(clock, register)

            // after
            if (inProgress?.completionClock == clock) {
                inProgress.onComplete()
                inProgress = null
            }
        }
    }
}
