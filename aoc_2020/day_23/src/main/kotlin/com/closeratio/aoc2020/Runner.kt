package com.closeratio.aoc2020

object Runner : AbstractRunner<String>() {

    private val game = CupGame("614752839")

    override fun part1(): String = game.iterate(100)

    override fun part2(): String = ""

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}