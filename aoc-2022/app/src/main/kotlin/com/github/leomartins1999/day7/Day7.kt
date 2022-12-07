package com.github.leomartins1999.day7

import com.github.leomartins1999.Day
import java.util.ArrayDeque

class Day7 : Day {

    override fun part1(input: String): Int {
        val directories = getDirectories(input)

        return directories
            .map { it.size(directories) }
            .filter { it < 100000 }
            .sum()
    }

    override fun part2(input: String): Int {
        val directories = getDirectories(input)

        val availableSize = 70000000 - directories.first().size(directories)

        return directories
            .map { it.size(directories) }
            .sorted()
            .first { it + availableSize > 30000000 }
    }

    private fun getDirectories(input: String): List<Directory> {
        val lines = input.split("\n").filter { it.isNotBlank() }

        val commands = getCommands(lines)

        return runCommands(commands, lines)
    }

    private fun getCommands(lines: List<String>) = lines
        .mapIndexed { idx, line -> Pair(idx, line) }
        .filter { it.second.startsWith("$") }
        .map { (idx, line) -> line.toCommand(idx) }

    private fun String.toCommand(index: Int): Command {
        val tokens = removePrefix("$ ")
            .split(" ")
            .filter { it.isNotBlank() }

        val argument =
            if (tokens.size > 1) tokens[1]
            else null

        return Command(
            index = index,
            verb = tokens[0],
            argument = argument
        )
    }

    private fun runCommands(commands: List<Command>, lines: List<String>): List<Directory> {
        val directories = mutableListOf<Directory>()

        val pwd = ArrayDeque<String>()

        commands.forEach { cmd ->
            when (cmd.verb) {
                "cd" -> handleCd(pwd, cmd.argument)
                "ls" -> handleLs(cmd, lines, pwd.reversed().joinToString(separator = "/"), directories)
                else -> throw IllegalArgumentException("Unknown command $cmd!")
            }
        }

        return directories
    }

    private fun handleCd(pwd: ArrayDeque<String>, argument: String?) {
        when (argument) {
            "/" -> {
                pwd.removeAll { true }
                pwd.push("/")
            }

            ".." -> pwd.pop()
            else -> pwd.push(argument!!)
        }
    }

    private fun handleLs(command: Command, lines: List<String>, pwd: String, directories: MutableList<Directory>) {
        val lsStart = command.index + 1

        val fromCommandToEnd = lines.subList(lsStart, lines.size)
        val indexOfNextCommand = fromCommandToEnd.indexOfFirst { it.startsWith("$") }

        val lsEnd =
            if (indexOfNextCommand == -1) lines.size
            else indexOfNextCommand + lsStart

        val lsLines = lines.subList(lsStart, lsEnd)

        val dir = Directory(
            name = pwd,
            files = getLsFiles(lsLines),
            nestedDirectories = getLsDirectories(lsLines, pwd)
        )

        directories += dir
    }

    private fun getLsFiles(lines: List<String>) = lines
        .filterNot { it.isLsDirectory() }
        .map { it.split(" ") }
        .map { File(name = it[1], size = it[0].toInt()) }

    private fun getLsDirectories(lines: List<String>, pwd: String) = lines
        .filter { it.isLsDirectory() }
        .map { it.split(" ")[1] }
        .map { "$pwd/$it" }

    private fun String.isLsDirectory() = split(" ").first() == "dir"

    private data class Command(
        val index: Int,
        val verb: String,
        val argument: String?
    )

    private data class Directory(
        val name: String,
        val files: List<File>,
        val nestedDirectories: List<String>
    ) {
        fun size(directories: List<Directory>) = sizeOfFiles() + sizeOfNestedDirectories(directories)

        private fun sizeOfFiles() = files.sumOf { it.size }

        private fun sizeOfNestedDirectories(directories: List<Directory>): Int =
            nestedDirectories
                .map { dirName -> directories.first { dir -> dirName == dir.name } }
                .sumOf { it.size(directories) }
    }

    private data class File(
        val name: String,
        val size: Int
    )
}
