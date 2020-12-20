package com.closeratio.aoc2020

class LeafRule(
    id: RuleId,
    val string: String
) : Rule(id) {

    override fun getMatchingSubstrings(
        ruleMap: Map<RuleId, Rule>,
        message: String,
        index: Int
    ): List<String> {
        // If our index is past the end of the string or this rule doesn't match the current string index, we don't have
        // a match so return empty list (which represents no matches)
        if (index >= message.length || !message.substring(index).startsWith(string)) {
            return emptyList()
        }

        // Otherwise we have a match, so return the message built it up this point with this rule's string appended
        return listOf(
            message.substring(0, index) + string
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LeafRule) return false

        if (id != other.id) return false
        if (string != other.string) return false

        return true
    }

    override fun hashCode(): Int {
        return string.hashCode()
    }

    override fun toString(): String {
        return "LeafRule('$string')"
    }

}