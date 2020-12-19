package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class RuleTest {

    @Test
    fun getValidStringCombinations() {
        val rule5 = LeafRule(5, "b")
        val rule4 = LeafRule(4, "a")

        val rule3 = BranchRule(
            3,
            listOf(
                listOf(rule4, rule5),
                listOf(rule5, rule4)
            )
        )

        val rule2 = BranchRule(
            2,
            listOf(
                listOf(rule4, rule4),
                listOf(rule5, rule5)
            )
        )

        val rule1 = BranchRule(
            1,
            listOf(
                listOf(rule2, rule3),
                listOf(rule3, rule2)
            )
        )

        val rule0 = BranchRule(
            0,
            listOf(
                listOf(rule4, rule1, rule5)
            )
        )

        val combinations = rule0.getValidStringCombinations()

        assertThat(combinations, hasSize(8))
        assertThat(
            combinations, hasItems(
                "aaaabb",
                "aaabab",
                "abbabb",
                "abbbab",
                "aabaab",
                "aabbbb",
                "abaaab",
                "ababbb"
            )
        )
    }

}