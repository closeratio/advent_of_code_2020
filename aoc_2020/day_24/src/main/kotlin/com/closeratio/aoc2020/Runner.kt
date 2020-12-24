package com.closeratio.aoc2020

object Runner : AbstractRunner<Int>() {


    override fun part1(): Int = Lobby().flipTiles(
        DirectionList.parse(javaClass.getResource("/input.txt").readText())
    ).filter { it.colour == Colour.BLACK }.size

    override fun part2(): Int = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}