package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val schedule = BusSchedule.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = schedule.calculateBusAndWaitTime().let { (bus, waitTime) ->
        bus.id * waitTime
    }

    override fun part2(): Long = schedule.calculateSynchronisationTime()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}