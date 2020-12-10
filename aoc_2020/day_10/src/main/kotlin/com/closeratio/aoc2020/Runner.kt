package com.closeratio.aoc2020

object Runner : AbstractRunner<Int>() {

    private val bag = Bag.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = bag.joltageDifferences()
        .let {
            it.getValue(1) * it.getValue(3)
        }

    override fun part2(): Int = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}