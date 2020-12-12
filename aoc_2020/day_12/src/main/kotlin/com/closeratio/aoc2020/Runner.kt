package com.closeratio.aoc2020

object Runner : AbstractRunner<Int>() {

    private val ship = Ship.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = ship.executeInstructions()

    override fun part2(): Int = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}