package com.closeratio.aoc2020

object Runner: AbstractRunner<Int>() {

    private val luggageComputer = LuggageComputer.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = luggageComputer.calculateBagCount(Colour("shiny gold"))

    override fun part2(): Int = 0

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}