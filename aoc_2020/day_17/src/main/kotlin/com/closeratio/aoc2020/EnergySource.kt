package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec

data class EnergySource(
    val initialState: Set<Vec>
) {

    fun iterate(count: Int): Int {
        val states = mutableListOf(initialState)

        repeat(count) {
            val lastState = states.last()
            states += lastState
                .flatMap { it.adjacent() + it }
                .toSet()
                .mapNotNull { vec ->
                    val adjacent = vec.adjacent().filter { it in lastState }
                    val surroundingActive = adjacent.intersect(lastState)

                    if (vec in lastState) {
                        if (surroundingActive.size in 2..3) vec else null
                    } else {
                        if (surroundingActive.size == 3) vec else null
                    }
                }
                .toSet()
        }

        return states.last().size
    }

    companion object {

        fun from(
            input: String,
            creator: (Int, Int) -> Vec
        ): EnergySource = input
            .trim()
            .split("\n")
            .map { it.trim() }
            .flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, character ->
                    when (character) {
                        '#' -> creator(x, y)
                        else -> null
                    }
                }
            }
            .toSet()
            .let {
                EnergySource(it)
            }

    }

}