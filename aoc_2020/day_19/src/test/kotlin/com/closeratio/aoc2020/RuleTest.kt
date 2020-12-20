package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.collection.IsEmptyCollection.empty
import org.junit.jupiter.api.Test

class RuleTest {

    @Test
    fun getValidStringCombinations() {
        val rule5 = LeafRule(RuleId(5), "b")
        val rule4 = LeafRule(RuleId(4), "a")

        val rule3 = BranchRule(
            RuleId(3),
            listOf(
                listOf(RuleId(4), RuleId(5)),
                listOf(RuleId(5), RuleId(4))
            )
        )

        val rule2 = BranchRule(
            RuleId(2),
            listOf(
                listOf(RuleId(4), RuleId(4)),
                listOf(RuleId(5), RuleId(5))
            )
        )

        val rule1 = BranchRule(
            RuleId(1),
            listOf(
                listOf(RuleId(2), RuleId(3)),
                listOf(RuleId(3), RuleId(2))
            )
        )

        val rule0 = BranchRule(
            RuleId(0),
            listOf(
                listOf(RuleId(4), RuleId(1), RuleId(5))
            )
        )

        val ruleMap = listOf(
            rule0, rule1, rule2, rule3, rule4, rule5
        ).associateBy { it.id }

        val combinations = rule0.getMatchingSubstrings(ruleMap, "ababbb", 0)

        assertThat(combinations, not(empty()))
    }

}