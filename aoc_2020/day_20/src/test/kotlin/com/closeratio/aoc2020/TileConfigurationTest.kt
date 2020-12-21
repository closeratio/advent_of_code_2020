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
        val permutations = tileConfiguration.permutations()

        assertThat(
            permutations.map { it.generateImage() },
            hasItem(javaClass.getResource("/test_input_1_expected_image.txt").readText())
        )
    }

    @Test
    fun calculateWaterRoughness() {
        val result = tileConfiguration.calculateWaterRoughness(
            SearchImage.from(javaClass.getResource("/search_image.txt").readText())
        )

        assertThat(
            result,
            `is`(273)
        )
    }

}