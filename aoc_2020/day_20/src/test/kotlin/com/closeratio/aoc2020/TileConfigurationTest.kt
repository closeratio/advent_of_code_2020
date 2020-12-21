package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsIterableContaining.hasItem
import org.junit.jupiter.api.Test

class TileConfigurationTest {

    private val tileConfiguration = TileConfiguration.computeFrom(
        TileParser.parseTiles(javaClass.getResource("/test_input_1.txt").readText())
    )

    @Test
    fun checksum() {
        assertThat(tileConfiguration.checksum(), `is`(20_899_048_083_289L))
    }

    @Test
    fun generateImage() {
        val permutations = listOf(
            tileConfiguration,
            tileConfiguration.rotate(),
            tileConfiguration.rotate().rotate(),
            tileConfiguration.rotate().rotate().rotate(),
            tileConfiguration.flip(),
            tileConfiguration.flip().rotate(),
            tileConfiguration.flip().rotate().rotate(),
            tileConfiguration.flip().rotate().rotate().rotate()
        )

        assertThat(
            permutations.map { it.generateImage() },
            hasItem(javaClass.getResource("/test_input_1_expected_image.txt").readText())
        )
    }

}