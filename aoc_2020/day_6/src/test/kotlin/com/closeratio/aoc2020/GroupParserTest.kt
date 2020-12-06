package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class GroupParserTest {

    @Test
    fun parseInput1(
    ) {
        val groups = GroupParser.parse(javaClass.getResource("/test_input_1.txt").readText())
        assertThat(groups, hasSize(1))

        assertThat(
            groups.first(),
            `is`(
                Group(
                    listOf(
                        Person(setOf('a', 'b', 'c', 'x')),
                        Person(setOf('a', 'b', 'c', 'y')),
                        Person(setOf('a', 'b', 'c', 'z'))
                    )
                )
            )
        )
    }

    @Test
    fun parseInput2(
    ) {
        val groups = GroupParser.parse(javaClass.getResource("/test_input_2.txt").readText())
        assertThat(groups, hasSize(5))
    }

}