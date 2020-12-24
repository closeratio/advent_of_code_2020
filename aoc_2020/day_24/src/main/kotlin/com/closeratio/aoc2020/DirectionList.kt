package com.closeratio.aoc2020

import com.closeratio.aoc2020.Direction.*

data class DirectionList(
    val directions: List<Direction>
) {

    companion object {

        fun parse(
            input: String
        ): List<DirectionList> = input
            .trim()
            .split("\n")
            .map { DirectionList(parseDirections(it)) }

        private fun parseDirections(
            line: String
        ): List<Direction> {
            if (line.isEmpty()) {
                return emptyList()
            }

            val (direction, dropCount) = when {
                line.startsWith("e") -> EAST to 1
                line.startsWith("w") -> WEST to 1
                line.startsWith("se") -> SOUTHEAST to 2
                line.startsWith("sw") -> SOUTHWEST to 2
                line.startsWith("ne") -> NORTHEAST to 2
                line.startsWith("nw") -> NORTHWEST to 2
                else -> throw IllegalArgumentException("Unrecognised direction: ${line.substring(0, 2)}")
            }

            return listOf(direction) + parseDirections(line.drop(dropCount))
        }
    }

}