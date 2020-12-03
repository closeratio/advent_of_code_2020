package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

class SlopeMap(
    val width: Int,
    val height: Int,
    val trees: Set<Vec2i>
) {

    fun treeCount(
        direction: Vec2i
    ): Long {
        val positions = arrayListOf(Vec2i.ZERO)

        while (positions.last().y < height - 1) {
            positions += positions.last() + direction
        }

        return positions
            .filter { containsTree(it) }
            .size
            .toLong()
    }

    private fun containsTree(pos: Vec2i): Boolean {
        if (pos.y >= height) {
            throw IllegalArgumentException("${pos.y} is out of height range (0-${height - 1}")
        }

        return trees.contains(
            Vec2i(
                pos.x % width,
                pos.y
            )
        )
    }

    companion object {

        fun from(input: String): SlopeMap {
            val lines = input.trim().split("\n")
            val height = lines.size
            val width = lines.first().length

            return SlopeMap(
                width,
                height,
                lines.flatMapIndexed { y: Int, line: String ->
                    line.mapIndexedNotNull { x, character ->
                        if (character == '#') {
                            Vec2i(x, y)
                        } else {
                            null
                        }
                    }
                }.toSet()
            )
        }

    }

}