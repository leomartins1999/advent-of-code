package com.github.leomartins1999.day8

import com.github.leomartins1999.Day

private typealias TreeHeight = Int

private typealias Forest = List<List<TreeHeight>>

class Day8 : Day {

    override fun part1(input: String) = parseInput(input).countVisibleTrees()
    override fun part2(input: String) = parseInput(input).getBestScenicScore()

    fun getScenicScore(input: String, x: Int, y: Int) = parseInput(input).getScenicScore(x, y)

    private fun parseInput(input: String): Forest {
        val lines = input.split("\n").filter { it.isNotBlank() }

        return lines.map { l -> l.map { c -> c.digitToInt() } }
    }

    private fun Forest.countVisibleTrees() = (0 until getXLength())
        .flatMap { x -> (0 until getYLength()).map { y -> Pair(x, y) } }
        .count { (x, y) -> isTreeVisible(x, y) }

    private fun Forest.isTreeVisible(x: Int, y: Int) = getSurroundingTrees(x, y).any { trees ->
        val max = trees.maxOrNull() ?: return true

        max < getHeight(x, y)
    }

    private fun Forest.getBestScenicScore(): Int {
        var max = -1

        for (x in 0 until first().size) {
            for (y in indices) {
                val score = getScenicScore(x, y)

                if (max < score) max = score
            }
        }

        return max
    }

    private fun Forest.getScenicScore(x: Int, y: Int) = getSurroundingTrees(x, y)
        .map { trees ->
            val height = this[y][x]

            var scenicScore = 0

            for (tree in trees) {
                scenicScore++
                if (tree >= height) break
            }

            scenicScore
        }
        .fold(1) { acc, score -> acc * score }

    private fun Forest.getSurroundingTrees(x: Int, y: Int) = listOf(
        getAboveTrees(x, y),
        getBelowTrees(x, y),
        getLeftTrees(x, y),
        getRightTrees(x, y)
    )

    private fun Forest.getAboveTrees(x: Int, y: Int) = subList(0, y).map { it[x] }.reversed()
    private fun Forest.getBelowTrees(x: Int, y: Int) = subList(y + 1, size).map { it[x] }
    private fun Forest.getLeftTrees(x: Int, y: Int) = this[y].subList(0, x).reversed()
    private fun Forest.getRightTrees(x: Int, y: Int) = this[y].subList(x + 1, first().size)

    private fun Forest.getXLength() = first().size
    private fun Forest.getYLength() = size

    private fun Forest.getHeight(x: Int, y: Int) = this[y][x]
}
