package com.closeratio.aoc2020

object Runner: AbstractRunner<Int>() {

    val boardingPasses = javaClass.getResource("/input.txt")
        .readText()
        .trim()
        .split("\n")
        .map { BoardingPass(it.trim()) }

    override fun part1(): Int = boardingPasses
        .map { it.seatId() }
        .maxOrNull()!!

    override fun part2(): Int = boardingPasses
        .asSequence()
        .map { it.seatId() }
        .sorted()
        .windowed(2, step = 1, partialWindows = false)
        .mapNotNull { (left, right) ->
            if (right - left > 1) {
                left + 1
            } else {
                null
            }
        }
        .first()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}