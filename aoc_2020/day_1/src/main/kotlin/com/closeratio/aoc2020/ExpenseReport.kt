package com.closeratio.aoc2020

class ExpenseReport(
        val items: List<Long>
) {

    fun calculatePairProduct(
            desiredSum: Long
    ): Long {
        val pair= items
                .asSequence()
                .mapIndexed { index, value ->
                    value to ArrayList(items).apply { removeAt(index) }
                }
                .flatMap { (value, sublist) ->
                    sublist.map { value to it }
                }
                .filter {
                    (left, right) -> left + right == desiredSum
                }
                .firstOrNull()
                ?: throw IllegalStateException("Unable to find pair which sums to $desiredSum")

        return pair.first * pair.second
    }

    companion object {
        fun from(input: String): ExpenseReport = input
                .trim()
                .split("\n")
                .map { it.toLong() }
                .let {
                    ExpenseReport(it)
                }
    }

}