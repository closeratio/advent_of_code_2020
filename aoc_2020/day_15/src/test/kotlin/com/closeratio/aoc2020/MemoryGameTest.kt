package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MemoryGameTest {

    @ParameterizedTest
    @MethodSource("getGameInputs")
    fun getNthNumber(
        input: List<Long>,
        requestedIndex: Long,
        expected: Long
    ) {

        val game = MemoryGame(input)
        val result = game.getNthNumber(requestedIndex)

        assertThat(result, `is`(expected))

    }

    companion object {

        @JvmStatic
        fun getGameInputs(): List<Arguments> = listOf(
            Arguments.of(listOf(0L, 3L, 6L), 2020, 436),
            Arguments.of(listOf(1L, 3L, 2L), 2020, 1),
            Arguments.of(listOf(2L, 1L, 3L), 2020, 10),
            Arguments.of(listOf(1L, 2L, 3L), 2020, 27),
            Arguments.of(listOf(2L, 3L, 1L), 2020, 78),
            Arguments.of(listOf(3L, 2L, 1L), 2020, 438),
            Arguments.of(listOf(3L, 1L, 2L), 2020, 1836),

            Arguments.of(listOf(0L, 3L, 6L), 30_000_000, 175594),
            Arguments.of(listOf(1L, 3L, 2L), 30_000_000, 2578),
            Arguments.of(listOf(2L, 1L, 3L), 30_000_000, 3544142),
            Arguments.of(listOf(1L, 2L, 3L), 30_000_000, 261214),
            Arguments.of(listOf(2L, 3L, 1L), 30_000_000, 6895259),
            Arguments.of(listOf(3L, 2L, 1L), 30_000_000, 18),
            Arguments.of(listOf(3L, 1L, 2L), 30_000_000, 362)
        )

    }

}