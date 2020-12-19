package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class MessageProcessorTest {

    private val processor = MessageProcessor.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
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

        assertThat(processor.rules, hasSize(6))
        assertThat(
            processor.rules, hasItems(
                rule0, rule1, rule2, rule3, rule4, rule5
            )
        )

        assertThat(processor.messages, hasSize(5))
        assertThat(
            processor.messages, hasItems(
                Message("ababbb"),
                Message("bababa"),
                Message("abbbab"),
                Message("aaabbb"),
                Message("aaaabbb")
            )
        )
    }

    @Test
    fun getMatchingMessages() {
        val result = processor.getMatchingMessages(0)

        assertThat(result, `is`(2))
    }

}