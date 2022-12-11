package com.github.leomartins1999.day11

import com.github.leomartins1999.Day

private typealias MonkeyId = Int
private typealias ItemWorryLevel = Long
private typealias ItemInspection = (ItemWorryLevel) -> ItemWorryLevel
private typealias ItemTest = (ItemWorryLevel) -> Boolean
private typealias ItemRelief = (ItemWorryLevel) -> ItemWorryLevel

class Day11 : Day {

    override fun part1(input: String) = input
        .parse()
        .getMonkeyBusinessLevel(20) { it / 3 }

    override fun part2(input: String): Long {
        val monkeys = input.parse()
        val lmc = monkeys
            .map { it.testDivisor }
            .distinct()
            .fold(1L) { acc, div -> acc * div }

        return monkeys.getMonkeyBusinessLevel(10000) { it % lmc }
    }

    private fun String.parse(): List<Monkey> {
        val lines = this
            .split("\n")
            .filter { it.isNotBlank() }
            .map(String::trim)

        return lines
            .asSequence()
            .mapIndexed { idx, l -> Pair(idx, l) }
            .filter { (_, l) -> l.startsWith("Monkey ") }
            .map { (idx, _) -> (idx..idx + 5) }
            .map { range -> lines.subList(range.first, range.last + 1) }
            .map { monkeyLines -> monkeyLines.buildMonkey() }
            .toList()
    }

    private fun List<String>.buildMonkey(): Monkey {
        val id = this[0].removePrefix("Monkey ")
            .removeSuffix(":")
            .toInt()

        val items = this[1].removePrefix("Starting items: ")
            .split(", ")
            .map(String::toLong)
            .toMutableList()

        val inspection = this[2].buildInspection()

        val testDivisor = this[3].removePrefix("Test: divisible by ")
            .toLong()

        val recipients = Pair(
            this[4].removePrefix("If true: throw to monkey ").toInt(),
            this[5].removePrefix("If false: throw to monkey ").toInt()
        )

        return Monkey(
            id = id,
            items = items,
            inspection = inspection,
            testDivisor = testDivisor,
            recipients = recipients
        )
    }

    private fun String.buildInspection(): ItemInspection {
        val tokens = this.removePrefix("Operation: new = old ")
            .split(" ")

        val operation: (ItemWorryLevel, ItemWorryLevel) -> ItemWorryLevel =
            when (val operStr = tokens[0]) {
                "*" -> Long::times
                "+" -> Long::plus
                else -> throw IllegalArgumentException("Unknown operation $operStr ($this)!")
            }

        val arg = tokens[1].toLongOrNull()

        return { old ->
            if (arg == null) operation(old, old)
            else operation(old, arg)
        }
    }

    private fun List<Monkey>.getMonkeyBusinessLevel(roundCnt: Int, relief: ItemRelief) = this
        .apply { simulateRounds(roundCnt, relief) }
        .map { it.itemsInspected }
        .sortedDescending()
        .subList(0, 2)
        .fold(1L) { acc, v -> acc * v }

    private fun List<Monkey>.simulateRounds(roundCnt: Int, relief: ItemRelief) {
        repeat(roundCnt) { _ ->
            this.forEach { monkey ->
                monkey.items.forEach { item ->
                    val newWorry = monkey.inspection(item)
                    val worryWithRelief = relief(newWorry)

                    val recipient =
                        if (worryWithRelief % monkey.testDivisor == 0L) monkey.recipients.first
                        else monkey.recipients.second

                    this[recipient].items += worryWithRelief
                    monkey.itemsInspected++
                }

                monkey.items.removeAll { true }
            }
        }
    }

    private data class Monkey(
        val id: MonkeyId,
        val items: MutableList<ItemWorryLevel>,
        val inspection: ItemInspection,
        val testDivisor: Long,
        val recipients: Pair<MonkeyId, MonkeyId>,
        var itemsInspected: Int = 0
    )

}