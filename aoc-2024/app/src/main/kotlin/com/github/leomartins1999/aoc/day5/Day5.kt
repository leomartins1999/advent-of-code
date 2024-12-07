package com.github.leomartins1999.aoc.day5

import com.github.leomartins1999.aoc.Day

class Day5(private val input: String) : Day {
    override fun part1(): Int {
        val (rules, updates) = parseInput()
        val validator = PageUpdateValidator(rules)

        return updates
            .filter { validator.call(it) }
            .sumOf { it.middleUpdate() }
    }

    private fun parseInput(): Pair<List<PageOrderingRule>, List<PageUpdate>> {
        val lines = input.lines()
        val indexOfEmptyLine = lines.indexOfFirst { it.isBlank() }

        val rules = lines.subList(0, indexOfEmptyLine).map { parseRule(it) }
        val updates = lines
            .subList(indexOfEmptyLine + 1, lines.size)
            .filter { it.isNotEmpty() }
            .map { parseUpdate(it) }

        return rules to updates
    }

    private fun parseRule(line: String): PageOrderingRule {
        val (page, beforePage) = line.split("|").map { it.toInt() }
        return PageOrderingRule(page, beforePage)
    }

    private fun parseUpdate(line: String): PageUpdate {
        val updates = line.split(",").map { it.toInt() }
        return PageUpdate(updates)
    }
}

data class PageOrderingRule(val page: Int, val beforePage: Int)
data class PageUpdate(val updates: List<Int>) {
    fun middleUpdate(): Int {
        return updates[updates.size / 2]
    }
}

class PageUpdateValidator(private val rules: List<PageOrderingRule>) {
    private val pageToBeforePages = rules
        .groupBy({ it.page }, { it.beforePage })
        .mapValues { it.value.toSet() }

    fun call(update: PageUpdate): Boolean {
        val pages = mutableSetOf<Int>()

        update.updates.forEach { page ->
            val beforePages = pageToBeforePages[page] ?: emptySet()
            if (pages.intersect(beforePages).isNotEmpty()) return false

            pages += page
        }

        return true
    }
}
