package com.github.leomartins1999.aoc.day4

import com.github.leomartins1999.aoc.Day

class Day4(private val input: String) : Day {
    override fun part1(): Any {
        return parseInput().count("XMAS")
    }

    private fun parseInput(): WordSearcher {
        val lines = input
            .lines()
            .map { it.toList() }
            .filter { it.isNotEmpty() }

        return WordSearcher(lines)
    }
}

class WordSearcher(private val lines: List<List<Char>>) {
    fun count(word: String): Int {
        val lines = getHorizontalLines() + getVerticalLines() + getDiagonalLines()

        return lines.fold(0) { acc, line -> acc + line.countOccurrences(word) }
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

        val bottomLeftToTopRight = (0 until width()).map { x -> diagonalStartingFrom(x, height() - 1, goingDown = false) }
        val leftToToRight = (0 until height() - 1).map { y -> diagonalStartingFrom(0, y, goingDown = false) }

        return topLeftToBottomRight + leftToBottomRight + bottomLeftToTopRight + leftToToRight
    }

    private fun diagonalStartingFrom(x: Int, y: Int, goingDown: Boolean = true): Line {
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

    private fun isValid(x: Int, y: Int) = x in 0 until width() && y in 0 until height()

    private fun valueOf(x: Int, y: Int) = lines[y][x]

    private fun height() = lines.size
    private fun width() = lines.first().size
}

class Line(private val chars: List<Char>) {
    fun countOccurrences(word: String): Int {
        var res = 0

        val currentChars = mutableListOf<Char>()
        chars.forEach { char ->
            currentChars.addLast(char)
            if (currentChars.size > word.length) currentChars.removeFirst()

            if (currentChars == word.toList() || currentChars == word.reversed().toList()) {
                res++
            }
        }

        return res
    }
}
