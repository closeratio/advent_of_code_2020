package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class InitialisationProgramTest {

    private val schedule = InitialisationProgram.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {

        assertThat(schedule.instructions, hasSize(4))
        assertThat(
            schedule.instructions, contains(
                SetMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"),
                UpdateMemory(8, 11),
                UpdateMemory(7, 101),
                UpdateMemory(8, 0)
            )
        )

    }

    @Test
    fun executeAndSumV1() {
        val result = schedule.executeAndSumV1()

        assertThat(result, Matchers.`is`(165L))
    }

    @Test
    fun executeAndSumV2() {
        val result = InitialisationProgram.from(javaClass.getResource("/test_input_2.txt").readText()).executeAndSumV2()

        assertThat(result, Matchers.`is`(208L))
    }

}