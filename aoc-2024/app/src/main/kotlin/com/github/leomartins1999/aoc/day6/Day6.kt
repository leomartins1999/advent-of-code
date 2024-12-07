package com.github.leomartins1999.aoc.day6

import com.github.leomartins1999.aoc.Day

class Day6(private val input: String) : Day {
    override fun part1(): Int {
        val map = parseInput()

        return map.visitedPositions().count()
    }

    private fun parseInput(): LabMap {
        val lines =
            input
                .lines()
                .filter(String::isNotEmpty)
                .map { it.toList() }

        return LabMap(lines)
    }
}

class LabMap(private val map: List<List<Char>>) {
    private val startingPosition = '^'
    private val obstacle = '#'

    fun visitedPositions(): Set<Position> {
        var currentPosition = findStartingPosition()
        var currentDirection = Direction.UP

        val visited = mutableSetOf<Position>()

        while (isWithinBounds(currentPosition)) {
            visited.add(currentPosition)

            val next = currentPosition.walk(currentDirection)
            if (!isWithinBounds(next)) break

            if (valueOf(next) == obstacle) {
                currentDirection = currentDirection.turn()
                continue
            }

            currentPosition = next
        }

        return visited
    }

    private fun findStartingPosition(): Position {
        map.forEachIndexed { y, line ->
            line.forEachIndexed { x, pos ->
                if (pos == startingPosition) return Position(x, y)
            }
        }

        throw RuntimeException("Starting position not found!")
    }

    private fun isWithinBounds(position: Position) = position.x in 0 until width() && position.y in 0 until height()

    private fun valueOf(position: Position) = map[position.y][position.x]

    private fun width() = map.first().size

    private fun height() = map.size
}

enum class Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    ;

    fun turn(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }
}

data class Position(val x: Int, val y: Int) {
    fun walk(direction: Direction): Position {
        return when (direction) {
            Direction.UP -> Position(x, y - 1)
            Direction.RIGHT -> Position(x + 1, y)
            Direction.DOWN -> Position(x, y + 1)
            Direction.LEFT -> Position(x - 1, y)
        }
    }
}
