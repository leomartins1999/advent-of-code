package com.github.leomartins1999.day13

import com.github.leomartins1999.Day

class Day13 : Day {

    override fun part1(input: String) = input
        .parse()
        .mapIndexed { idx, packets -> Pair(idx + 1, packets) }
        .filter { (_, packets) -> packets.inRightOrder() == ComparisonResult.RIGHT }
        .sumOf { (idx, _) -> idx }

    override fun part2(input: String) = input
        .parse()
        .flatMap { listOf(it.left, it.right) }
        .asSequence()
        .plus(dividerPackets)
        .sortedWith { p1, p2 ->
            when (p1.isRightOrder(p2)) {
                ComparisonResult.RIGHT -> -1
                ComparisonResult.NOT_RIGHT -> 1
                else -> 0
            }
        }
        .mapIndexed { idx, p -> Pair(idx, p) }
        .filter { (_, p) -> p in dividerPackets }
        .map { (idx, _) -> idx + 1 }
        .fold(1) { acc, idx -> acc * idx }

    private fun String.parse() = split("\n")
        .filter { it.isNotBlank() }
        .map { it.toPacket() }
        .chunked(2)
        .map { (first, second) -> PacketPair(first, second) }

    private fun String.toPacket(): PacketList {
        val parts = mutableListOf<Packet>()

        var idx = 1

        while (idx < length - 1) {
            val char = this[idx]

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

        return PacketList(parts = parts)
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

    private companion object {
        val dividerPackets = listOf(buildDividerPacket(2), buildDividerPacket(6))

        private fun buildDividerPacket(v: Int) =
            PacketList(parts = listOf(PacketList(parts = listOf(PacketBit(v = v)))))
    }

    private enum class ComparisonResult { RIGHT, NOT_RIGHT, CONTINUE }

    private interface Packet {
        fun isRightOrder(other: Packet): ComparisonResult
    }

    private data class PacketBit(val v: Int) : Packet {

        override fun isRightOrder(other: Packet): ComparisonResult {
            if (other is PacketBit) {
                return when {
                    v == other.v -> ComparisonResult.CONTINUE
                    v < other.v -> ComparisonResult.RIGHT
                    else -> ComparisonResult.NOT_RIGHT
                }
            }

            return PacketList(parts = listOf(this)).isRightOrder(other)
        }
    }

    private data class PacketList(val parts: List<Packet>) : Packet {

        override fun isRightOrder(other: Packet) =
            if (other is PacketBit) isRightOrder(PacketList(parts = listOf(other)))
            else isRightOrder(other as PacketList)

        private fun isRightOrder(other: PacketList): ComparisonResult {
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

    private data class PacketPair(val left: Packet, val right: Packet) {
        fun inRightOrder() = left.isRightOrder(right)
    }
}
