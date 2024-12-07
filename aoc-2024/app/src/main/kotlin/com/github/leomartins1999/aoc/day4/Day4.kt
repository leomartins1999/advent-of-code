package com.github.leomartins1999.aoc.day4

import com.github.leomartins1999.aoc.Day

class Day4(private val input: String) : Day {
    override fun part1(): Int {
        return parseInput().countXMASs()
    }

    override fun part2(): Int {
        return parseInput().countMASXs()
    }

    private fun parseInput(): WordSearcher {
        val lines =
            input
                .lines()
                .map { it.toList() }
                .filter { it.isNotEmpty() }

        return WordSearcher(lines)
    }
}

class WordSearcher(private val lines: List<List<Char>>) {
    private val mas = "MAS".toList()

    fun countXMASs(): Int {
        val lines = getHorizontalLines() + getVerticalLines() + getDiagonalLines()

        return lines.fold(0) { acc, line -> acc + line.countXMASs() }
    }

    fun countMASXs(): Int {
        var res = 0

        (1 until width() - 1).forEach { x ->
            (1 until height() - 1).forEach { y ->
                if (isMASX(x, y)) res++
            }
        }

        return res
    }

    private fun getHorizontalLines(): List<Line> {
        return lines.map { Line(it) }
    }

    private fun getVerticalLines(): List<Line> {
        return (0 until width()).map { x ->
            val chars = (0 until height()).map { y -> valueOf(x, y) }

            Line(chars)
        }
    }

    private fun getDiagonalLines(): List<Line> {
        val topLeftToBottomRight = (0 until width()).map { x -> diagonalStartingFrom(x, 0) }
        val leftToBottomRight = (1 until height()).map { y -> diagonalStartingFrom(0, y) }

        val bottomLeftToTopRight =
            (0 until width()).map { x -> diagonalStartingFrom(x, height() - 1, goingDown = false) }
        val leftToToRight = (0 until height() - 1).map { y -> diagonalStartingFrom(0, y, goingDown = false) }

        return topLeftToBottomRight + leftToBottomRight + bottomLeftToTopRight + leftToToRight
    }

    private fun diagonalStartingFrom(
        x: Int,
        y: Int,
        goingDown: Boolean = true,
    ): Line {
        val chars = mutableListOf<Char>()

        var currX = x
        var currY = y
        while (isValid(currX, currY)) {
            chars.add(valueOf(currX, currY))

            if (goingDown) {
                currX++
                currY++
            } else {
                currX++
                currY--
            }
        }

        return Line(chars)
    }

    private fun isMASX(
        x: Int,
        y: Int,
    ): Boolean {
        val topToBot = listOf(valueOf(x - 1, y - 1), valueOf(x, y), valueOf(x + 1, y + 1))
        val botToTop = listOf(valueOf(x - 1, y + 1), valueOf(x, y), valueOf(x + 1, y - 1))

        return (topToBot == mas || topToBot == mas.reversed()) && (botToTop == mas || botToTop == mas.reversed())
    }

    private fun isValid(
        x: Int,
        y: Int,
    ) = x in 0 until width() && y in 0 until height()

    private fun valueOf(
        x: Int,
        y: Int,
    ) = lines[y][x]

    private fun height() = lines.size

    private fun width() = lines.first().size
}

class Line(private val chars: List<Char>) {
    private val xmas = "XMAS"

    fun countXMASs(): Int {
        var res = 0

        val currentChars = mutableListOf<Char>()
        chars.forEach { char ->
            currentChars.addLast(char)
            if (currentChars.size > xmas.length) currentChars.removeFirst()

            if (currentChars == xmas.toList() || currentChars == xmas.reversed().toList()) {
                res++
            }
        }

        return res
    }
}
