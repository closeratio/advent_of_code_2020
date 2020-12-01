package com.closeratio.aoc2020

class ExpenseReport(
        val items: List<Long>
) {

    fun calculatePairProduct(
            desiredSum: Long
    ): Long {
        val pair = items
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

    fun calculateTripleProduct(
            desiredSum: Long
    ): Long {
        val triple = items
                .asSequence()
                .mapIndexed { index, value ->
                    value to ArrayList(items).apply { removeAt(index) }
                }
                .flatMap { (firstValue, sublist) ->
                    sublist.flatMap {
                        sublist.mapIndexed { index, secondValue ->
                            Triple(
                                    firstValue,
                                    secondValue,
                                    ArrayList(items).apply { removeAt(index) }
                            )
                        }
                    }
                }
                .flatMap { (first, second, third) ->
                    third.map {
                        Triple(first, second, it)
                    }
                }
                .filter {
                    (first, second, third) -> first + second + third == desiredSum
                }
                .firstOrNull()
                ?: throw IllegalStateException("Unable to find triple which sums to $desiredSum")

        return triple.first * triple.second * triple.third
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