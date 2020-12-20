package com.closeratio.aoc2020

class BranchRule(
    id: RuleId,
    val combinations: List<List<RuleId>>
) : Rule(id) {

    override fun getMatchingSubstrings(
        ruleMap: Map<RuleId, Rule>,
        message: String,
        index: Int
    ): List<String> = combinations.flatMap { combination ->
        combination.fold(
            // Seed the matched string with the substring of the message up to this index
            arrayListOf(listOf(message.substring(0, index)))
        ) { acc, ruleId ->
            val rule = ruleMap.getValue(ruleId)

            acc += acc.last().flatMap { matchedSubstring ->
                rule.getMatchingSubstrings(ruleMap, message, matchedSubstring.length)
            }

            acc
        }.last()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BranchRule) return false

        if (id != other.id) return false
        if (combinations != other.combinations) return false

        return true
    }

    override fun hashCode(): Int {
        return combinations.hashCode()
    }

    override fun toString(): String {
        return "BranchRule($combinations)"
    }

}
