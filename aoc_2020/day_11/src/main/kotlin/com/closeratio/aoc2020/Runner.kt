package com.closeratio.aoc2020

object Runner : AbstractRunner<Int>() {

    private val room = WaitingRoom.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = room.iterateUntilStableSimple()

    override fun part2(): Int = room.iterateUntilStableComplex()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}