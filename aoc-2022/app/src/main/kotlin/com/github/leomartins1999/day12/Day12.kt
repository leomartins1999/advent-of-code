package com.github.leomartins1999.day12

import com.github.leomartins1999.Day

private typealias HeightMap = List<List<Char>>

class Day12 : Day {

    override fun part1(input: String) = with(input.parse()) { getFewestSteps() }

    override fun part2(input: String) = with(input.parse()) {
        val positions = height.getPositionsForHeight('a')

        positions.minOfOrNull { pos -> getFewestSteps(pos) }
    }

    private fun String.parse(): Map {
        val map = split("\n")
            .filter { it.isNotBlank() }
            .map { it.toList() }

        val start = map.getPosition('S')
        val end = map.getPosition('E')

        val height = map
            .replace('S', 'a')
            .replace('E', 'z')

        return Map(
            start = start,
            end = end,
            height = height
        )
    }

    private fun HeightMap.replace(old: Char, new: Char): HeightMap = this
        .map { line -> line.toMutableList() }
        .toMutableList()
        .apply {
            val (x, y) = getPosition(old)
            this[y][x] = new
        }
        .map { it.toList() }
        .toList()

    private fun HeightMap.getPosition(c: Char): Position {
        val y = indexOfFirst { c in it }
        val x = this[y].indexOf(c)

        return Position(x, y)
    }

    private fun HeightMap.getPositionsForHeight(height: Char) = indices
        .flatMap { y ->
            this[y]
                .mapIndexed { x, height -> Pair(x, height) }
                .filter { (_, h) -> height == h }
                .map { (x, _) -> Position(x = x, y = y) }
        }

    private data class Map(
        val start: Position,
        val end: Position,
        val height: HeightMap
    ) {
        fun getFewestSteps(initialPosition: Position = start): Int {
            val cost = mutableMapOf(initialPosition to 0)
            val toVisit = mutableSetOf(initialPosition)

            while (toVisit.isNotEmpty()) {
                val node = toVisit.first()
                toVisit.remove(node)

                val nodeCost = cost[node] ?: throw IllegalStateException("Cost for node $node not found!")

                val surroundingNodes = node.getSurroundingNodes()

                surroundingNodes.forEach { surroundingNode ->
                    val surroundingCost = cost.getOrElse(surroundingNode) { Int.MAX_VALUE }
                    if (surroundingCost >= nodeCost + 1) {
                        cost[surroundingNode] = nodeCost + 1
                        toVisit += (surroundingNode)
                    }
                }
            }

            return cost[end] ?: Int.MAX_VALUE
        }

        private fun Position.getSurroundingNodes() =
            listOfNotNull(
                getPositionOrNull(x + 1, y),
                getPositionOrNull(x - 1, y),
                getPositionOrNull(x, y + 1),
                getPositionOrNull(x, y - 1),
            ).filter { other ->
                val positionHeight = this.getHeight()
                val otherHeight = other.getHeight()

                positionHeight >= otherHeight - 1
            }

        private fun Position.getHeight() = height[y][x]

        private fun getPositionOrNull(x: Int, y: Int) =
            if (x < 0 || y < 0 || x >= height.first().size || y >= height.size) null
            else Position(x, y)
    }

    private data class Position(val x: Int, val y: Int)

}