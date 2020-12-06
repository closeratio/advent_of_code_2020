package com.closeratio.aoc2020

object Runner: AbstractRunner<Int>() {

    private val groups = GroupParser.parse(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = groups
        .map { it.allAnswerCount() }
        .sum()

    override fun part2(): Int = groups
        .map { it.commonAnswerCount() }
        .sum()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}