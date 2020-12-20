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
        val result = processor.getMatchingMessages(RuleId(0))

        assertThat(result, `is`(2))
    }

    @Test
    fun getMatchingMessagesAdvanced() {
        val result = MessageProcessor
            .from(javaClass.getResource("/test_input_2.txt").readText())
            .getMatchingMessages(RuleId(0))

        assertThat(result, `is`(12))
    }

}