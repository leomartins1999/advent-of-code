package com.github.leomartins1999.day2

import java.lang.IllegalStateException

class Day2 {

    fun part1(input: String) = parseInput(input).sumOf { it.getPart1Points() }

    fun part2(input: String) = parseInput(input).sumOf { it.getPart2Points() }

    private fun parseInput(input: String) =
        input
            .split("\n")
            .map { it.trim() }
            .map { it.split(" ") }
            .filter { it.size == 2 }
            .map { it.toRound() }

    private fun List<String>.toRound() =
        Round(
            oponentPlay = this[0].toPlay(),
            myPlay = this[1].toPlay(),
            outcome = this[1].toOutcome()
        )

    private fun String.toPlay() = when(this) {
        "A", "X" -> Play.Rock
        "B", "Y" -> Play.Paper
        "C", "Z" -> Play.Scissors
        else -> throw IllegalStateException("Unknown play $this!")
    }

    private fun String.toOutcome() = when(this) {
        "X" -> Outcome.Lose
        "Y" -> Outcome.Draw
        "Z" -> Outcome.Win
        else -> throw IllegalStateException("Unknown outcome $this!")
    }

    private fun Round.getPart1Points() = getOutcomePoints() + myPlay.points

    private fun Round.getOutcomePoints() = when {
        oponentPlay == myPlay -> 3
        myPlay.beats(oponentPlay) -> 6
        else -> 0
    }

    private fun Round.getPart2Points() = outcome.points + playForOutcome(outcome).points

    private fun Round.playForOutcome(outcome: Outcome) = when (outcome) {
        Outcome.Draw -> oponentPlay
        Outcome.Win -> oponentPlay.getBeatedBy()
        Outcome.Lose -> oponentPlay.getBeats()
    }

    private data class Round(
        val oponentPlay: Play,
        val myPlay: Play,
        val outcome: Outcome
    )

    private enum class Play(
        val points: Int
    ) {
        Rock(1), Paper(2), Scissors(3);

        fun beats(otherPlay: Play) =
            (this == Rock && otherPlay == Scissors)
            || (this == Paper && otherPlay == Rock)
            || (this == Scissors && otherPlay == Paper)

        fun getBeatedBy() = when (this) {
            Rock -> Paper
            Scissors -> Rock
            Paper -> Scissors
        }

        fun getBeats() = when (this) {
            Rock -> Scissors
            Scissors -> Paper
            Paper -> Rock
        }
    }

    private enum class Outcome(
        val points: Int
    ) {
        Win(6), Draw(3), Lose(0)
    }

}