package com.closeratio.aoc2020

import kotlin.system.measureTimeMillis

abstract class AbstractRunner<T> {

    abstract fun part1(): T

    abstract fun part2(): T

    fun runBothParts() {
        val part1Time = measureTimeMillis {
            val result = part1()
            println("Part 1 result: $result")
        }
        println("Part 1 took ${part1Time}ms")

        val part2Time = measureTimeMillis {
            val result = part2()
            println("Part 2 result: $result")
        }
        println("Part 2 took ${part2Time}ms")
    }

}