package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BagTest {

    @ParameterizedTest
    @MethodSource("getParseData")
    fun parse(
        data: String,
        expectedColour: Colour,
        expectedContents: Map<Colour, Long>
    ) {
        val rule = Bag.parse(data)

        assertThat(rule.colour, `is`(expectedColour))

        expectedContents.forEach { (colour, count) ->
            assertThat(rule.contents, hasEntry(colour, count))
        }
    }

    companion object {

        @JvmStatic
        fun getParseData(): List<Arguments> = listOf(
            Arguments.of(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                Colour("light red"),
                mapOf(
                    Colour("bright white") to 1L,
                    Colour("muted yellow") to 2L
                )
            ),
            Arguments.of(
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                Colour("dark orange"),
                mapOf(
                    Colour("bright white") to 3L,
                    Colour("muted yellow") to 4L
                )
            ),
            Arguments.of(
                "bright white bags contain 1 shiny gold bag.",
                Colour("bright white"),
                mapOf(
                    Colour("shiny gold") to 1L
                )
            ),
            Arguments.of(
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                Colour("muted yellow"),
                mapOf(
                    Colour("shiny gold") to 2L,
                    Colour("faded blue") to 9L
                )
            ),
            Arguments.of(
                "faded blue bags contain no other bags.",
                Colour("faded blue"),
                emptyMap<Colour, Long>()
            ),
            Arguments.of(
                "dotted black bags contain no other bags.",
                Colour("dotted black"),
                emptyMap<Colour, Long>()
            )
        )

    }

}