package com.github.leomartins1999.aoc.day8

import com.github.leomartins1999.aoc.Day

class Day8(private val input: String) : Day {
    override fun part1(): Int {
        val antennaMap = parseInput()

        return antennaMap.antinodes().count()
    }

    override fun part2(): Int {
        val antennaMap = parseInput(true)

        return antennaMap.antinodes().count()
    }

    private fun parseInput(withResonantHarmonics: Boolean = false): AntennaMap {
        val map = input
            .lines()
            .filter(String::isNotBlank)
            .map { it.toList() }

        return AntennaMap(map, withResonantHarmonics)
    }
}

class AntennaMap(private val map: List<List<Char>>, private val withResonantHarmonics: Boolean = false) {
    fun antinodes(): Set<Position> {
        val antennaToPositions = getAntennaToPositions()

        return antennaToPositions
            .values
            .fold(setOf()) { acc, positions -> acc + antinodesFor(positions) }
    }

    private fun getAntennaToPositions(): Map<Char, List<Position>> {
        val antennaToPositions = mutableMapOf<Char, MutableList<Position>>()

        map.forEachIndexed { y, row ->
            row.forEachIndexed rowLoop@{ x, cell ->
                if (cell == '.') return@rowLoop

                val positionList = antennaToPositions.getOrPut(cell) { mutableListOf() }
                positionList += Position(x, y)
            }
        }

        return antennaToPositions
    }

    private fun antinodesFor(positions: List<Position>): Set<Position> {
        val antinodePositions = mutableSetOf<Position>()

        (0 until positions.size - 1).forEach { firstIdx ->
            val first = positions[firstIdx]

            (firstIdx + 1 until positions.size).forEach { secondIdx ->
                val second = positions[secondIdx]

                antinodePositions += getPairAntinodes(first, second)
            }
        }

        return antinodePositions
    }

    private fun getPairAntinodes(first: Position, second: Position): Set<Position> {
        val dPos = Position(second.x - first.x, second.y - first.y)

        return getPositionAntinode(second, dPos) + getPositionAntinode(first, dPos.inverse())
    }

    private fun getPositionAntinode(position: Position, dPos: Position): Set<Position> {
        val positionAntinodes = mutableSetOf<Position>()
        if (withResonantHarmonics) positionAntinodes.add(position)

        var curr = position + dPos
        while (isValidPosition(curr)) {
            positionAntinodes.add(curr)
            if (!withResonantHarmonics) break

            curr += dPos
        }

        return positionAntinodes
    }

    private fun isValidPosition(position: Position) =
        position.x in 0 until width() && position.y in 0 until height()

    private fun width() = map.first().size
    private fun height() = map.size
}

data class Position(val x: Int, val y: Int) {
    fun inverse() = Position(-x, -y)

    operator fun plus(other: Position) = Position(x + other.x, y + other.y)
}
