package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

object Runner: AbstractRunner<Int>() {

    override fun part1(): Int = SlopeMap.from(javaClass.getResource("/input.txt").readText())
        .treeCount(Vec2i(3, 1))

    override fun part2(): Int = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}