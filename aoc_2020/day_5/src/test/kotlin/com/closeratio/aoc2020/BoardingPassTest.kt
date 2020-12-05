package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BoardingPassTest {

    companion object {

        @JvmStatic
        fun getSeatIdData(): List<Arguments> = listOf(
            Arguments.of("FBFBBFFRLR", 357),
            Arguments.of("BFFFBBFRRR", 567),
            Arguments.of("FFFBBBFRRR", 119),
            Arguments.of("BBFFBBFRLL", 820)
        )

    }

    @ParameterizedTest
    @MethodSource("getSeatIdData")
    fun seatId(
        data: String,
        expectedSeatId: Int
    ) {
        val boardingPass = BoardingPass(data)

        assertThat(boardingPass.seatId(), `is`(expectedSeatId))
    }

}