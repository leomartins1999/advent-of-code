package com.github.leomartins1999.day9

import com.github.leomartins1999.Day
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day9 : Day {

    override fun part1(input: String) = parseInput(input).simulateMoves()
    override fun part2(input: String) = parseInput(input).simulateMoves(knots = 10)

    private fun parseInput(input: String) = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toMove() }

    private fun String.toMove() = split(" ")
        .filter { it.isNotBlank() }
        .let { Move(direction = it[0], steps = it[1].toInt()) }

    private fun List<Move>.simulateMoves(knots: Int = 2) =
        fold(State(knots = knots)) { state, move ->
            (0 until move.steps)
                .fold(state) { partialState, _ -> move(partialState, move.direction) }
        }
            .visitedPositions
            .size

    private fun move(state: State, direction: String) = with(state) {
        val newHead = buildNewHead(head, direction)
        val newTails = buildTails(tails, newHead)
        val newVisitedPositions = visitedPositions + newTails.last()

        copy(head = newHead, tails = newTails, visitedPositions = newVisitedPositions)
    }

    private fun buildNewHead(head: Position, direction: String) = with(head) {
        when (direction) {
            "U" -> copy(y = y + 1)
            "D" -> copy(y = y - 1)
            "R" -> copy(x = x + 1)
            "L" -> copy(x = x - 1)
            else -> throw IllegalArgumentException("Unknown direction $direction!")
        }
    }

    private fun buildTails(tails: List<Position>, newFirstHead: Position): List<Position> =
        tails.foldIndexed(emptyList()) { idx, acc, tail ->
            val newHead = if (idx == 0) newFirstHead else acc[idx - 1]
            val newTail = if (tail closeTo newHead) tail else tail.follow(newHead)

            acc + newTail
        }

    private fun Position.follow(other: Position) = copy(
        x = x + (other.x - x).sign,
        y = y + (other.y - y).sign
    )

    private data class Move(val direction: String, val steps: Int)

    private data class Position(val x: Int, val y: Int) {
        infix fun closeTo(other: Position) = (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1
    }

    private data class State(
        val head: Position = Position(x = 0, y = 0),
        val knots: Int = 2,
        val tails: List<Position> = List(knots - 1) { head },
        val visitedPositions: Set<Position> = emptySet()
    )
}
