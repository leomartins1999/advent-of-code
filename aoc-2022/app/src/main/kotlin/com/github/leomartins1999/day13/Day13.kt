package com.github.leomartins1999.day13

import com.github.leomartins1999.Day

class Day13 : Day {

    override fun part1(input: String) = input
        .parse()
        .mapIndexed { idx, packets -> Pair(idx + 1, packets) }
        .filter { (_, packets) -> packets.inRightOrder() == ComparisonResult.RIGHT }
        .onEach { (idx, _) -> println("$idx has right order!") }
        .sumOf { (idx, _) -> idx }

    private fun String.parse() = split("\n")
        .filter { it.isNotBlank() }
        .map { it.toPacket() }
        .chunked(2)
        .map { (first, second) -> Packets(first, second) }

    private fun String.toPacket(): Packet {
        println("Converting $this to packet")

        val parts = mutableListOf<PacketPart>()

        var idx = 1

        while (idx < length - 1) {
            val char = this[idx]
            println("-> Parsing $char (idx: $idx)")

            if (char == '[') {
                val lastIdx = getPacketLastIndex(idx)
                parts += substring(idx..lastIdx).toPacket()
                idx = lastIdx + 2
            } else {
                val bitLastIdx = substring(idx until length)
                    .indexOfFirst { it == ',' }
                    .let {
                        if (it == -1) length - 2
                        else it - 1 + idx
                    }

                parts += PacketBit(v = substring(idx..bitLastIdx).toInt())
                idx = bitLastIdx + 2
            }
        }

        return Packet(parts = parts)
    }

    private fun String.getPacketLastIndex(firstIndex: Int): Int {
        var cnt = 0

        (firstIndex + 1 until length)
            .forEach { idx ->
                when (this[idx]) {
                    '[' -> cnt++
                    ']' -> cnt--
                }

                if (cnt < 0) return idx
            }

        return -1
    }

    private interface PacketPart {
        fun isRightOrder(other: PacketPart): ComparisonResult
    }

    private data class PacketBit(val v: Int) : PacketPart {
        override fun toString() = v.toString()

        override fun isRightOrder(other: PacketPart): ComparisonResult {
            if (other is PacketBit) {
                return when {
                    v == other.v -> ComparisonResult.CONTINUE
                    v < other.v -> ComparisonResult.RIGHT
                    else -> ComparisonResult.NOT_RIGHT
                }
            }

            return Packet(parts = listOf(this)).isRightOrder(other)
        }
    }

    private data class Packet(val parts: List<PacketPart>) : PacketPart {
        override fun toString() = "[${parts.joinToString(separator = ",")}]"

        override fun isRightOrder(other: PacketPart) =
            if (other is PacketBit) isRightOrder(Packet(parts = listOf(other)))
            else isRightOrder(other as Packet)

        private fun isRightOrder(other: Packet): ComparisonResult {
            parts.forEachIndexed { idx, part ->
                val otherPart = other.parts.getOrNull(idx) ?: return ComparisonResult.NOT_RIGHT

                val result = part.isRightOrder(otherPart)

                if (result != ComparisonResult.CONTINUE) return result
            }

            return when {
                parts.size < other.parts.size -> ComparisonResult.RIGHT
                parts.size == other.parts.size -> ComparisonResult.CONTINUE
                else -> ComparisonResult.NOT_RIGHT
            }
        }
    }

    private enum class ComparisonResult { RIGHT, NOT_RIGHT, CONTINUE }

    private data class Packets(val left: Packet, val right: Packet) {
        fun inRightOrder() = left
            .isRightOrder(right)
            .also { println("$this\nright order? $it") }

        override fun toString() = "Packet 1: $left\nPacket 2: $right"
    }
}