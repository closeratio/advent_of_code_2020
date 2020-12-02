package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PasswordRulePairTest {

    @Test
    fun firstValidOldRules() {
        assertThat(
            PasswordRulePair(
                Rule(1, 3, 'a'),
                "abcde"
            ).isValidOldRules(),
            `is`(true)
        )
    }

    @Test
    fun secondValidOldRules() {
        assertThat(
            PasswordRulePair(
                Rule(1, 3, 'b'),
                "cdefg"
            ).isValidOldRules(),
            `is`(false)
        )
    }

    @Test
    fun thirdValidOldRules() {
        assertThat(
            PasswordRulePair(
                Rule(2, 9, 'c'),
                "ccccccccc"
            ).isValidOldRules(),
            `is`(true)
        )
    }

    @Test
    fun firstValidCurrentRules() {
        assertThat(
            PasswordRulePair(
                Rule(1, 3, 'a'),
                "abcde"
            ).isValidCurrentRules(),
            `is`(true)
        )
    }

    @Test
    fun secondValidCurrentRules() {
        assertThat(
            PasswordRulePair(
                Rule(1, 3, 'b'),
                "cdefg"
            ).isValidCurrentRules(),
            `is`(false)
        )
    }

    @Test
    fun thirdValidCurrentRules() {
        assertThat(
            PasswordRulePair(
                Rule(2, 9, 'c'),
                "ccccccccc"
            ).isValidCurrentRules(),
            `is`(false)
        )
    }

}