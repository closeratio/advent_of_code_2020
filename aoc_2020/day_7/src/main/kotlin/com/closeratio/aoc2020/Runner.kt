package com.closeratio.aoc2020

object Runner: AbstractRunner<Int>() {

    private val luggageComputer = LuggageComputer.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Int = luggageComputer.calculateValidBags(Colour("shiny gold"))

    override fun part2(): Int = luggageComputer.calculateChildBagCount(Colour("shiny gold"))

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}