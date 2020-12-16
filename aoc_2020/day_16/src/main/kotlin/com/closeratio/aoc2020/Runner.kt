package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val processor = TicketProcessor.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = processor.getInvalidValueSum()

    override fun part2(): Long = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}