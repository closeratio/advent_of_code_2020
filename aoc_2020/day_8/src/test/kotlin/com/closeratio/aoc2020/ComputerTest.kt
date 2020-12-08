package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class ComputerTest {

    private val computer = Computer.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
        assertThat(computer.instructions, hasSize(9))

        assertThat(
            computer.instructions, contains(
                NoOp(),
                Accumulate(1),
                Jump(4),
                Accumulate(3),
                Jump(-3),
                Accumulate(-99),
                Accumulate(1),
                Jump(-4),
                Accumulate(6)
            )
        )
    }

    @Test
    fun iterateUntilInstructionRepeated() {
        computer.iterateUntilInstructionRepeated()

        assertThat(computer.accumulator, `is`(5))
    }

}