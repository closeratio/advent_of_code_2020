package com.closeratio.aoc2020

data class MessageProcessor(
    val rules: List<Rule>,
    val messages: List<Message>
) {

    fun getMatchingMessages(ruleId: RuleId): Int {
        val ruleMap = rules.associateBy { it.id }
        val startingRule = ruleMap.getValue(ruleId)

        return messages
            .filter { message ->
                val matchingSubstrings = startingRule.getMatchingSubstrings(
                    ruleMap,
                    message.value,
                    0
                )

                matchingSubstrings.any { it.length == message.value.length }
            }
            .size
    }

    companion object {

        fun from(input: String): MessageProcessor {
            val (ruleChunk, messageChunk) = input.trim()
                .split("\n\n")

            val rules: List<Rule> = ruleChunk
                .split("\n")
                .map { it.trim() }
                .map { parseRule(it) }

            val messages = messageChunk
                .split("\n")
                .map { it.trim() }
                .map { Message(it) }

            return MessageProcessor(
                rules,
                messages
            )
        }

        private fun parseRule(
            ruleString: String
        ): Rule {
            val (idString, ruleBody) = ruleString
                .split(":")
                .map { it.trim() }

            val id = RuleId(idString.toInt())

            if (ruleBody.startsWith("\"")) {
                return LeafRule(id, ruleBody.trim('"'))
            }

            val combinationStrings = ruleBody
                .split("|")
                .map { it.trim() }

            return BranchRule(
                id,
                combinationStrings.map { combination ->
                    combination
                        .split(" ")
                        .map {
                            RuleId(it.toInt())
                        }

                }
            )
        }

    }

}

