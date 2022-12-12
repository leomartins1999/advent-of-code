package com.github.leomartins1999.day12

import com.github.leomartins1999.Day

private typealias Height = Char
private typealias HeightMap = List<List<Height>>

class Day12 : Day {

    override fun part1(input: String) = input.parse().getFewestSteps()

    override fun part2(input: String) = with(input.parse()) {
        heightMap
            .getPositionsForHeight('a')
            .minOfOrNull { pos -> getFewestSteps(pos) }
    }

    private fun String.parse(): Map {
        val map = split("\n")
            .filter { it.isNotBlank() }
            .map { it.toList() }

        val start = map.getPositionsForHeight('S').first()
        val end = map.getPositionsForHeight('E').first()

        val heightMap = map
            .replace('S', 'a')
            .replace('E', 'z')

        return Map(
            start = start,
            end = end,
            heightMap = heightMap
        )
    }

    private fun HeightMap.replace(old: Height, new: Height) = this
        .map { line -> line.toMutableList() }
        .toMutableList()
        .apply {
            val (x, y) = getPositionsForHeight(old).first()
            this[y][x] = new
        }
        .map { it.toList() }
        .toList()

    private fun HeightMap.getPositionsForHeight(height: Height) = indices
        .flatMap { y ->
            this[y]
                .mapIndexed { x, height -> Pair(x, height) }
                .filter { (_, h) -> height == h }
                .map { (x, _) -> Position(x = x, y = y) }
        }

    private data class Map(
        val start: Position,
        val end: Position,
        val heightMap: HeightMap
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

        private fun getPositionOrNull(x: Int, y: Int) =
            if (x < 0 || y < 0 || x >= heightMap.first().size || y >= heightMap.size) null
            else Position(x, y)

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

        private fun Position.getHeight() = heightMap[y][x]
    }

    private data class Position(val x: Int, val y: Int)
}
