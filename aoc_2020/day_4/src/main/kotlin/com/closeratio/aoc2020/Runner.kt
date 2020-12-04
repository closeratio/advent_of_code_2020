package com.closeratio.aoc2020

object Runner: AbstractRunner<Long>() {

    val passportData = BatchFileParser.parse(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = passportData
        .filter { it.isLooselyValid() }
        .size
        .toLong()

    override fun part2(): Long = passportData
        .filter { it.isStronglyValid() }
        .size
        .toLong()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}