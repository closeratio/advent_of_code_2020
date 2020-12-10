package com.closeratio.aoc2020

data class Bag(
    val adapters: List<Adapter>
) {

    val deviceJoltage = adapters.maxByOrNull { it.joltage }!!.joltage + 3

    fun joltageDifferences(): Map<Int, Int> = (listOf(0, deviceJoltage) + adapters.map { it.joltage })
        .sorted()
        .windowed(2, step = 1, partialWindows = false)
        .map { (left, right) -> right - left }
        .groupBy { it }
        .mapValues { (_, values) -> values.size }

    companion object {

        fun from(data: String): Bag = data
            .trim()
            .split("\n")
            .map { Adapter(it.trim().toInt()) }
            .let { Bag(it) }

    }

}