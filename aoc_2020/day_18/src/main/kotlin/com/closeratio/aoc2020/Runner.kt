package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val expressions = javaClass.getResource("/input.txt").readText()
        .trim()
        .split("\n")
        .map { ExpressionParser.parse(it) }

    override fun part1(): Long = expressions.map { it.evaluate() }.sum()

    override fun part2(): Long = 0L

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}