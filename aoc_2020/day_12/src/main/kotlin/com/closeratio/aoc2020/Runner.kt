package com.closeratio.aoc2020

object Runner : AbstractRunner<Int>() {

    private val ship = Ship.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = ship.moveSimple()

    override fun part2(): Int = ship.moveComplex()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}