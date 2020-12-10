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

    fun validArrangements(): Long = (listOf(0, deviceJoltage) + adapters.map { it.joltage })
        .sorted()
        .fold(arrayListOf<ArrayList<Long>>()) { acc, curr ->
            // Create a new group if the list is empty or the diff to the last group is > 2
            // Otherwise append this joltage to the current group
            if (acc.isEmpty() || curr - acc.last().last() > 2) {
                acc.add(arrayListOf(curr.toLong()))
            } else {
                acc.last().add(curr.toLong())
            }

            acc
        }
        .filter { it.size > 1 } // Groups with single elements don't matter
        .map {
            childStateCount(
                listOf(it.first()),
                it.drop(1)
            )
        }
        .reduce { acc, curr -> acc * curr }

    private fun childStateCount(
        currList: List<Long>,
        remainingItems: List<Long>
    ): Long {
        if (remainingItems.isEmpty()) {
            return 1L
        }

        val last = currList.last()
        val candidates = remainingItems.filter { it - last <= 3 }

        return candidates.map { candidate ->
            childStateCount(
                currList + candidate,
                remainingItems.filter { it > candidate }
            )
        }.reduce { acc, curr -> acc + curr }
    }

    companion object {

        fun from(data: String): Bag = data
            .trim()
            .split("\n")
            .map { Adapter(it.trim().toInt()) }
            .let { Bag(it) }

    }

}