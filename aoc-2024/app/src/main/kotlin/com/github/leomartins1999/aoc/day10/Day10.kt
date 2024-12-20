package com.github.leomartins1999.aoc.day10

import com.github.leomartins1999.aoc.Day

class Day10(private val input: String) : Day {
    override fun part1(): Int {
        val map = parseInput()

        return map.trailheadScores().sum()
    }

    override fun part2(): Any {
        val map = parseInput()

        return map.trailheadRatings().sum()
    }

    private fun parseInput(): TopographicMap {
        val map =
            input
                .lines()
                .filter(String::isNotBlank)
                .map(String::toCharArray)
                .map { row -> row.map(Character::getNumericValue) }

        return TopographicMap(map)
    }
}

class TopographicMap(private val map: List<List<Int>>) {
    fun trailheadScores(): List<Int> {
        val trailheads = getTrailheads()

        return trailheads
            .map(::countReachableSummitsFrom)
            .map(Set<Position>::count)
    }

    fun trailheadRatings(): List<Int> {
        val trailheads = getTrailheads()

        return trailheads.map(::countPathToSummitsFrom)
    }

    private fun getTrailheads(): Set<Position> {
        val trailheads = mutableSetOf<Position>()

        (0 until height()).forEach { y ->
            (0 until width()).forEach { x ->
                if (altitudeOf(Position(x, y)) == TRAILHEAD_ALTITUDE) {
                    trailheads.add(Position(x, y))
                }
            }
        }

        return trailheads
    }

    private fun countReachableSummitsFrom(position: Position): Set<Position> {
        val currentAltitude = altitudeOf(position)
        val paths = nextPositionsFrom(position)

        if (currentAltitude == SUMMIT_ALTITUDE - 1) return paths

        return paths.fold(emptySet()) { acc, nextPosition -> acc + countReachableSummitsFrom(nextPosition) }
    }

    private fun countPathToSummitsFrom(position: Position): Int {
        val currentAltitude = altitudeOf(position)
        val paths = nextPositionsFrom(position)

        if (currentAltitude == SUMMIT_ALTITUDE - 1) return paths.count()

        return paths.sumOf(::countPathToSummitsFrom)
    }

    private fun nextPositionsFrom(position: Position): Set<Position> {
        val currentAltitude = altitudeOf(position)

        return position
            .getSurroundingPositions()
            .filter(::isValidPosition)
            .filter { altitudeOf(it) == currentAltitude + 1 }
            .toSet()
    }

    private fun isValidPosition(position: Position) = position.x in 0 until width() && position.y in 0 until height()

    private fun altitudeOf(position: Position) = map[position.y][position.x]

    private fun height() = map.size

    private fun width() = map.first().size

    private companion object {
        const val TRAILHEAD_ALTITUDE = 0
        const val SUMMIT_ALTITUDE = 9
    }
}

data class Position(val x: Int, val y: Int) {
    fun getSurroundingPositions(): Set<Position> {
        return setOf(
            Position(x - 1, y),
            Position(x + 1, y),
            Position(x, y - 1),
            Position(x, y + 1),
        )
    }
}
