package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val game = MemoryGame(
        listOf(
            5L, 1L, 9L, 18L, 13L, 8L, 0L
        )
    )

    override fun part1(): Long = game.getNthNumber(2020)

    override fun part2(): Long = 0L

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}