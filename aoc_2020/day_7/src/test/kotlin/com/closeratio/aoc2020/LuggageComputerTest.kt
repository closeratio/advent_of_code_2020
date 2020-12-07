package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class LuggageComputerTest {

    val computer = LuggageComputer.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun parse() {
        assertThat(computer.bags, hasSize(9))
    }

    @Test
    fun calculateBagCount() {
        assertThat(
            computer.calculateBagCount(Colour("shiny gold")),
            `is`(4)
        )
    }

}