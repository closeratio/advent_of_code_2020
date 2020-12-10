package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val bag = Bag.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = bag.joltageDifferences()
        .let {
            it.getValue(1) * it.getValue(3)
        }
        .toLong()

    override fun part2(): Long = bag.validArrangements()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}