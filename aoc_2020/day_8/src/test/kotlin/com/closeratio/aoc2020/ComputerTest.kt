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
    fun iterateUntilLoopingOrFinished_Looping() {
        val result = computer.iterateUntilLoopingOrFinished()

        assertThat(computer.accumulator, `is`(5))
        assertThat(result, `is`(false))
    }

    @Test
    fun iterateUntilLoopingOrFinished_Finished() {
        computer.instructions[7] = NoOp()

        val result = computer.iterateUntilLoopingOrFinished()

        assertThat(computer.accumulator, `is`(8))
        assertThat(result, `is`(true))
    }

}