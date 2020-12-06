package com.closeratio.aoc2020

object Runner: AbstractRunner<Int>() {

    private val groups = GroupParser.parse(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = groups
        .map { it.answerCount() }
        .sum()

    override fun part2(): Int = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}