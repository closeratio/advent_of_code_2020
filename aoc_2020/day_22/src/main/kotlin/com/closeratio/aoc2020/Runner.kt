package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val game = CombatGame.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = game.computeWinningScoreSimple()

    override fun part2(): Long = game.computeWinningScoreAdvanced().second // It's not 30462 - that's too low

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}