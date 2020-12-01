package com.closeratio.aoc2020

abstract class AbstractRunner {

    abstract fun part1()

    abstract fun part2()

    fun runBothParts() {
        runTimed(
                { println("Part 1 took ${it}ms") }
        ) { part1() }

        runTimed(
                { println("Part 2 took ${it}ms") }
        ) { part2() }
    }

}