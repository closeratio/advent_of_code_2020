package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class TileConfigurationTest {

    private val tileConfiguration = TileConfiguration.computeFrom(
        TileParser.parseTiles(javaClass.getResource("/test_input_1.txt").readText())
    )

    @Test
    fun checksum() {
        assertThat(tileConfiguration.checksum(), Matchers.`is`(20_899_048_083_289L))
    }

}