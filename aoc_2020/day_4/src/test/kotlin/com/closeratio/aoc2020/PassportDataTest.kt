package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PassportDataTest {

    companion object {

        @JvmStatic
        fun generateIsLooselyValidCombinations(): List<Arguments> = BatchFileParser
            .parse(javaClass.getResource("/test_input_loosely_valid_and_invalid.txt").readText())
            .let {
                listOf(
                    Arguments.of(it[0], true),
                    Arguments.of(it[1], false),
                    Arguments.of(it[2], true),
                    Arguments.of(it[3], false)
                )
            }

        @JvmStatic
        fun generateStronglyValidCombinations(): List<Arguments> = BatchFileParser
            .parse(javaClass.getResource("/test_input_strongly_invalid.txt").readText())
            .map { Arguments.of(it, false) } + BatchFileParser
            .parse(javaClass.getResource("/test_input_strongly_valid.txt").readText())
            .map { Arguments.of(it, true) }
    }

    @ParameterizedTest
    @MethodSource("generateIsLooselyValidCombinations")
    fun isLooselyValid(
        data: PassportData,
        valid: Boolean
    ) {
        assertThat(data.isLooselyValid(), `is`(valid))
    }

    @ParameterizedTest
    @MethodSource("generateStronglyValidCombinations")
    fun isStronglyValid(
        data: PassportData,
        valid: Boolean
    ) {
        assertThat(data.isStronglyValid(), `is`(valid))
    }

}