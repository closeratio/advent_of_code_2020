package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val program = InitialisationProgram.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = program.executeAndSum()

    override fun part2(): Long = 0L

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}