package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PasswordRulePairTest {

    @Test
    fun firstValid() {
        assertThat(
            PasswordRulePair(
                Rule(1, 3, 'a'),
                "abcde"
            ).isValid(),
            `is`(true)
        )
    }

    @Test
    fun secondValid() {
        assertThat(
            PasswordRulePair(
                Rule(1, 3, 'b'),
                "cdefg"
            ).isValid(),
            `is`(false)
        )
    }

    @Test
    fun thirdValid() {
        assertThat(
            PasswordRulePair(
                Rule(2, 9, 'c'),
                "ccccccccc"
            ).isValid(),
            `is`(true)
        )
    }

}