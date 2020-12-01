package com.closeratio.aoc2020

inline fun runTimed(
        logCallback: (Long) -> Unit,
        block: () -> Unit
) {
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()

    logCallback(end - start)
}