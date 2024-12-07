package com.github.leomartins1999.aoc.day7

import com.github.leomartins1999.aoc.Day

class Day7(private val input: String) : Day {
    override fun part1(): Long {
        val equations = parseInput()

        return equations
            .filter(Equation::isValid)
            .sumOf(Equation::result)
    }

    private fun parseInput(): List<Equation> {
        return input
            .lines()
            .filter(String::isNotEmpty)
            .map(::parseEquation)
    }

    private fun parseEquation(line: String): Equation {
        val (resultStr, elementsStr) = line.split(":")

        val result = resultStr
            .trim()
            .toLong()

        val elements = elementsStr
            .split(" ")
            .filter(String::isNotEmpty)
            .map(String::toLong)

        return Equation(result, elements)
    }
}

class Equation(val result: Long, private val elements: List<Long>) {
    fun isValid(): Boolean {
        return evaluate().any { it == result }
    }

    private fun evaluate(elems: List<Long> = elements): List<Long> {
        var results = mutableListOf<Long>()

        elems.forEach { elem ->
            if (results.isEmpty()) {
                results.add(elem)
                return@forEach
            }

            results = results.flatMap { res ->
                listOf(
                    res + elem,
                    res * elem
                )
            }.toMutableList()
        }

        return results
    }
}

