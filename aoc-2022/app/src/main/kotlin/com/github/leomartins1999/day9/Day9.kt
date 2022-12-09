package com.github.leomartins1999.day9

import com.github.leomartins1999.Day
import kotlin.math.absoluteValue

class Day9 : Day {

    override fun part1(input: String) = parseInput(input).simulateMoves()

    private fun parseInput(input: String) = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toMove() }

    private fun String.toMove() = split(" ")
        .filter { it.isNotBlank() }
        .let { Move(direction = it[0], steps = it[1].toInt()) }

    private fun List<Move>.simulateMoves() =
        fold(State()) { state, move ->
            (0 until move.steps)
                .fold(state) { partialState, _ -> move(partialState, move.direction) }
        }
            .visitedPositions
            .size

    private fun move(state: State, direction: String) = with(state) {
        val newHead = with(head) {
            when (direction) {
                "U" -> copy(y = y + 1)
                "D" -> copy(y = y - 1)
                "R" -> copy(x = x + 1)
                "L" -> copy(x = x - 1)
                else -> throw IllegalArgumentException("Unknown direction $direction!")
            }
        }

        val newTail =
            if (tail closeTo newHead) tail
            else head

        val newVisitedPositions = visitedPositions.toMutableSet() + newTail

        State(head = newHead, tail = newTail, visitedPositions = newVisitedPositions)
    }

    private data class Move(val direction: String, val steps: Int)

    private data class Position(val x: Int, val y: Int) {
        infix fun closeTo(other: Position) = (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1
    }

    private data class State(
        val head: Position = Position(x = 0, y = 0),
        val tail: Position = head,
        val visitedPositions: Set<Position> = emptySet()
    )
}
