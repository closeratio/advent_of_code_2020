package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class PasswordRuleParserTest {

    @Test
    fun parse() {
        val pairs = PasswordRuleParser.parse(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(pairs, hasSize(3))

        assertThat(
            pairs[0], `is`(
                PasswordRulePair(
                    Rule(1, 3, 'a'),
                    "abcde"
                )
            )
        )

        assertThat(
            pairs[1], `is`(
                PasswordRulePair(
                    Rule(1, 3, 'b'),
                    "cdefg"
                )
            )
        )

        assertThat(
            pairs[2], `is`(
                PasswordRulePair(
                    Rule(2, 9, 'c'),
                    "ccccccccc"
                )
            )
        )
    }

}