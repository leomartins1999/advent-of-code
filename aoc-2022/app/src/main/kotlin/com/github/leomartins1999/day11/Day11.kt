package com.github.leomartins1999.day11

import com.github.leomartins1999.Day

private typealias MonkeyId = Int
private typealias Item = Int
private typealias WorryLevel = Int
private typealias ItemInspection = (WorryLevel) -> WorryLevel
private typealias ItemTest = (WorryLevel) -> Boolean

class Day11 : Day {

    override fun part1(input: String) = input.parse().getMonkeyBusinessLevel(20)

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
            .map(String::toInt)
            .toMutableList()

        val inspection = this[2].buildInspection()

        val test = this[3].removePrefix("Test: divisible by ")
            .toInt()
            .let { { worryLevel: WorryLevel -> worryLevel % it == 0 } }

        val recipients = Pair(
            this[4].removePrefix("If true: throw to monkey ").toInt(),
            this[5].removePrefix("If false: throw to monkey ").toInt()
        )

        return Monkey(
            id = id,
            items = items,
            inspection = inspection,
            test = test,
            recipients = recipients
        )
    }

    private fun String.buildInspection(): ItemInspection {
        val tokens = this.removePrefix("Operation: new = old ")
            .split(" ")

        val operation: (Int, Int) -> Int = when (val operStr = tokens[0]) {
            "*" -> Int::times
            "+" -> Int::plus
            else -> throw IllegalArgumentException("Unknown operation $operStr ($this)!")
        }

        val arg = tokens[1].toIntOrNull()

        return { old ->
            if (arg == null) operation(old, old)
            else operation(old, arg)
        }
    }

    private fun List<Monkey>.getMonkeyBusinessLevel(roundCnt: Int) = this
        .apply { simulateRounds(roundCnt) }
        .map { it.itemsInspected }
        .sortedDescending()
        .subList(0, 2)
        .fold(1) { acc, v -> acc * v }

    private fun List<Monkey>.simulateRounds(roundCnt: Int) {
        repeat(roundCnt) { _ ->
            this.forEach { monkey ->
                monkey.items.forEach { item ->
                    val newWorry = monkey.inspection(item)
                    val worryWithRelief = newWorry / 3

                    val recipient =
                        if (monkey.test(worryWithRelief)) monkey.recipients.first
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
        val items: MutableList<Item>,
        val inspection: ItemInspection,
        val test: ItemTest,
        val recipients: Pair<MonkeyId, MonkeyId>,
        var itemsInspected: Int = 0
    )

}