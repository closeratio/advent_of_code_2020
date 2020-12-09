package com.closeratio.aoc2020

data class XmasList(
    val list: List<Long>,
    val windowSize: Int
) {

    fun findFirstInvalidNumber(): Long = list
        .windowed(windowSize + 1, step = 1, partialWindows = false) { window ->
            val target = window.last()
            val sublist = window.dropLast(1)

            val pair = sublist
                .asSequence()
                .flatMap { left ->
                    sublist
                        .filter { it != left }
                        .map { right -> left to right }
                }
                .filter { (left, right) -> left + right == target }
                .firstOrNull()

            if (pair == null) {
                target
            } else {
                null
            }
        }
        .filterNotNull()
        .first()

    fun findEncryptionWeakness(target: Long): Long = list
        .asSequence()
        .flatMapIndexed { startIndex, _ ->
            (startIndex..list.size).map { endIndex ->
                list.subList(startIndex, endIndex)
            }
        }
        .map { it to it.sum() }
        .first { (_, sum) -> sum == target }
        .let { (sublist, _) ->
            sublist.minOrNull()!! + sublist.maxOrNull()!!
        }

    companion object {

        fun from(
            data: String,
            windowSize: Int
        ): XmasList = XmasList(
            data
                .trim()
                .split("\n")
                .map { it.trim().toLong() },
            windowSize
        )

    }

}