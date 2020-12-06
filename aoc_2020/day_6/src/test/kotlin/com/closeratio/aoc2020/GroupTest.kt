package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class GroupTest {

    val group = Group(
        listOf(
            Person(setOf('a', 'b', 'c', 'x')),
            Person(setOf('a', 'b', 'c', 'y')),
            Person(setOf('a', 'b', 'c', 'z'))
        )
    )

    @Test
    fun allAnswerCount() {
        assertThat(group.allAnswerCount(), `is`(6))
    }

    @Test
    fun commonAnswerCount() {
        assertThat(group.commonAnswerCount(), `is`(3))
    }

}