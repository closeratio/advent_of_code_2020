package com.closeratio.aoc2020

data class MessageProcessor(
    val rules: List<Rule>,
    val messages: List<Message>
) {

    fun getMatchingMessages(ruleId: Int): Int {
        val validMessages = rules
            .find { it.id == ruleId }!!
            .getValidStringCombinations()
            .toSet()

        return messages
            .filter { it.value in validMessages }
            .size
    }

    companion object {

        fun from(input: String): MessageProcessor {
            val (ruleChunk, messageChunk) = input.trim()
                .split("\n\n")

            val ruleMap = hashMapOf<Int, Rule>()
            val ruleStrings: Map<Int, String> = ruleChunk
                .split("\n")
                .map { it.trim() }
                .associateBy { it.split(":").first().toInt() }

            ruleMap[0] = parseRule(
                ruleStrings.getValue(0),
                ruleStrings,
                ruleMap
            )

            val messages = messageChunk
                .split("\n")
                .map { it.trim() }
                .map { Message(it) }

            return MessageProcessor(
                ruleMap.values.toList(),
                messages
            )
        }

        private fun parseRule(
            ruleString: String,
            ruleStrings: Map<Int, String>,
            ruleMap: HashMap<Int, Rule>
        ): Rule {
            val (idString, ruleBody) = ruleString
                .split(":")
                .map { it.trim() }

            if (ruleBody.startsWith("\"")) {
                return LeafRule(
                    idString.toInt(),
                    ruleBody.trim('"')
                )
            }

            val combinationStrings = ruleBody
                .split("|")
                .map { it.trim() }

            return BranchRule(
                idString.toInt(),
                combinationStrings.map { combination ->
                    combination
                        .split(" ")
                        .map { it.toInt() }
                        .map { id ->
                            ruleMap.getOrPut(id) {
                                parseRule(
                                    ruleStrings.getValue(id),
                                    ruleStrings,
                                    ruleMap
                                )
                            }
                        }

                }
            )
        }

    }

}

