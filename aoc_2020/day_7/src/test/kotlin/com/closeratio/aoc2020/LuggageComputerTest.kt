package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class LuggageComputerTest {

    @Test
    fun parse() {
        assertThat(
            LuggageComputer.from(javaClass.getResource("/test_input_1.txt").readText()).bags,
            hasSize(9)
        )
    }

    @Test
    fun calculateValidBags() {
        assertThat(
            LuggageComputer.from(javaClass.getResource("/test_input_1.txt").readText()).calculateValidBags(Colour("shiny gold")),
            `is`(4)
        )
    }

    @Test
    fun calculateChildBagCount() {
        assertThat(
            LuggageComputer.from(javaClass.getResource("/test_input_2.txt").readText()).calculateChildBagCount(Colour("shiny gold")),
            `is`(126)
        )
    }

}