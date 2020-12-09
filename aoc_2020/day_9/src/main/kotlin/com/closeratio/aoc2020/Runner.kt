package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val xmasList = XmasList.from(
        javaClass.getResource("/input.txt").readText(),
        25
    )

    override fun part1(): Long = xmasList.findFirstInvalidNumber()

    override fun part2(): Long = xmasList.findEncryptionWeakness(32321523L)

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}