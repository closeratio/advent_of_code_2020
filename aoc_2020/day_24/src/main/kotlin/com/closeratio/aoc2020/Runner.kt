package com.closeratio.aoc2020

object Runner : AbstractRunner<Int>() {


    override fun part1(): Int = Lobby()
        .flipTiles(
            DirectionList.parse(javaClass.getResource("/input.txt").readText())
        )
        .filter { it.colour == Colour.BLACK }.size

    override fun part2(): Int = Lobby()
        .apply {
            flipTiles(
                DirectionList.parse(javaClass.getResource("/input.txt").readText())
            )
        }
        .evolve(100)
        .filter { it.colour == Colour.BLACK }.size

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}