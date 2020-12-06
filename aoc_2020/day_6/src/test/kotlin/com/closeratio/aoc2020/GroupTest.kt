package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class GroupTest {

    @Test
    fun answerCount() {
        val group = Group(
            listOf(
                Person(setOf('a', 'b', 'c', 'x')),
                Person(setOf('a', 'b', 'c', 'y')),
                Person(setOf('a', 'b', 'c', 'z'))
            )
        )

        assertThat(group.answerCount(), `is`(6))
    }

}