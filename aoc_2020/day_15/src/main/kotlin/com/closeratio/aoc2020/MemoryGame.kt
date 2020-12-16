package com.closeratio.aoc2020

class MemoryGame(
    val startingNumbers: List<Long>
) {

    fun getNthNumber(desiredIndex: Long): Long {

        val numberHistory: MutableMap<Long, ArrayList<Long>> = startingNumbers
            .mapIndexed { index, number -> number to arrayListOf(index.toLong()) }
            .toMap() as MutableMap
        var last = startingNumbers.last()

        (1L..desiredIndex)
            .drop(startingNumbers.size)
            .forEach { index ->

                last = if (numberHistory.getValue(last).size == 1) {
                    0
                } else {
                    val history = numberHistory.getValue(last)
                    val diff = history.last() - history[history.size - 2]
                    diff
                }

                numberHistory
                    .getOrPut(last) { arrayListOf() }.let {
                        it.add(index - 1)

                        while (it.size > 2) {
                            it.removeAt(0)
                        }
                    }
            }

        return last
    }

}