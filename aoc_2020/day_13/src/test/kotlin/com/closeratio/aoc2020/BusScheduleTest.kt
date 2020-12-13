package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class BusScheduleTest {

    private val schedule = BusSchedule.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {

        assertThat(
            schedule, `is`(
                BusSchedule(
                    setOf(
                        Bus(7),
                        Bus(13),
                        Bus(59),
                        Bus(31),
                        Bus(19)
                    ),
                    939L
                )
            )
        )

    }

    @Test
    fun calculateBusAndWaitTime() {

        val result = schedule.calculateBusAndWaitTime()

        assertThat(result.first, `is`(Bus(59)))
        assertThat(result.second, `is`(5))

    }

}