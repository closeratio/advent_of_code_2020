package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

object Runner: AbstractRunner<Long>() {

    val slopeMap = SlopeMap.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = slopeMap.treeCount(Vec2i(3, 1))

    override fun part2(): Long = listOf(
        Vec2i(1, 1),
        Vec2i(3, 1),
        Vec2i(5, 1),
        Vec2i(7, 1),
        Vec2i(1, 2)
    ).map { slopeMap.treeCount(it) }.fold(1) { acc, curr -> acc * curr }

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}