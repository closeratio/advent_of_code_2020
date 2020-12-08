package com.closeratio.aoc2020

object Runner: AbstractRunner<Long>() {

    override fun part1(): Long = Computer
        .from(javaClass.getResource("/input.txt").readText())
        .let {
            it.iterateUntilInstructionRepeated()
            it.accumulator
        }

    override fun part2(): Long = 0L

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}