package com.closeratio.aoc2020

object Runner: AbstractRunner<Long>() {

    override fun part1(): Long = BatchFileParser.parse(javaClass.getResource("/input.txt").readText())
        .filter { it.isValid() }
        .size
        .toLong()

    override fun part2(): Long = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}