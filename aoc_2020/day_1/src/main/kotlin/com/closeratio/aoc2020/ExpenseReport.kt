package com.closeratio.aoc2020

class ExpenseReport(
        val items: List<Long>
) {

    fun evaluateIndexes(
            indexes: Sequence<List<Int>>,
            desiredSum: Long
    ): Long = indexes
            // Check that indices are different
            .filter { HashSet(it).size == it.size }
            // Check that values sum to desired value
            .filter {
                (it.fold(0L) { acc, curr ->
                    acc + items[curr]
                }) == desiredSum
            }
            .map { it.fold(1L) { acc, curr -> acc * items[curr] } }
            .firstOrNull()
            ?: throw IllegalStateException("Unable to find set which sums to $desiredSum")

    fun calculatePairProduct(
            desiredSum: Long
    ): Long = IntRange(0, items.size - 1)
            .asSequence()
            .flatMap { firstIndex ->
                IntRange(0, items.size - 1).map {
                    listOf(firstIndex, it)
                }
            }
            .let { evaluateIndexes(it, desiredSum) }

    fun calculateTripleProduct(
            desiredSum: Long
    ): Long = IntRange(0, items.size - 1)
            .asSequence()
            .flatMap { firstIndex ->
                IntRange(0, items.size - 1).flatMap { secondIndex ->
                    IntRange(0, items.size - 1).map {
                        listOf(firstIndex, secondIndex, it)
                    }
                }
            }
            .let { evaluateIndexes(it, desiredSum) }

    companion object {
        fun from(input: String): ExpenseReport = input
                .trim()
                .split("\n")
                .map { it.toLong() }
                .sorted()
                .let {
                    ExpenseReport(it)
                }
    }

}