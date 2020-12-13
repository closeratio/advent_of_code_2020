package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BusScheduleTest {

    private val schedule = BusSchedule.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {

        assertThat(
            schedule, `is`(
                BusSchedule(
                    setOf(
                        Bus(7, 0),
                        Bus(13, 1),
                        Bus(59, 4),
                        Bus(31, 6),
                        Bus(19, 7)
                    ),
                    939L
                )
            )
        )

    }

    @Test
    fun calculateBusAndWaitTime() {

        val result = schedule.calculateBusAndWaitTime()

        assertThat(result.first, `is`(Bus(59, 4)))
        assertThat(result.second, `is`(5))

    }

    @ParameterizedTest(name = "[{0}] = {1}")
    @MethodSource("getSynchronisationTimeArgs")
    fun calculateSynchronisationTime(
        input: String,
        expected: Long
    ) {
        val schedule = BusSchedule.from(input)
        val result = schedule.calculateSynchronisationTime()

        assertThat(result, `is`(expected))
    }

    companion object {

        @JvmStatic
        fun getSynchronisationTimeArgs(): List<Arguments> = listOf(
            Arguments.of("0\n17,x,13,19", 3417L),
            Arguments.of("0\n67,7,59,61", 754018L),
            Arguments.of("0\n67,x,7,59,61", 779210L),
            Arguments.of("0\n67,7,x,59,61", 1261476L),
            Arguments.of("0\n1789,37,47,1889", 1202161486L),
            Arguments.of(BusScheduleTest::class.java.getResource("/test_input_1.txt").readText(), 1068781L)
        )

    }

}