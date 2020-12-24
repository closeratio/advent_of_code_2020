package com.closeratio.aoc2020

object Runner : AbstractRunner<String>() {


    override fun part1(): String = CupGame("614752839").getFinalString(100)

    override fun part2(): String = CupGame(
        "614752839".map {
            Cup(it.toString().toLong())
        } + (10..1_000_000).map {
            Cup(it.toLong())
        }
    ).getNextNCups(10_000_000, 1L, 2)
        .map { it.value }
        .reduce { acc, curr -> acc * curr }
        .toString()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}