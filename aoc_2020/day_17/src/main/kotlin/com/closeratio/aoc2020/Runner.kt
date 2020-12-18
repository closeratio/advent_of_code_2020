package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec3i
import com.closeratio.aoc2020.math.Vec4i

object Runner : AbstractRunner<Long>() {

    private val inputData = javaClass.getResource("/input.txt").readText()

    override fun part1(): Long = EnergySource.from(inputData) { x, y -> Vec3i(x, y, 0) }.iterate(6).toLong()

    override fun part2(): Long = EnergySource.from(inputData) { x, y -> Vec4i(x, y, 0, 0) }.iterate(6).toLong()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}