package com.closeratio.aoc2020

class LeafRule(
    id: Int,
    val string: String
): Rule(id) {

    override fun getValidStringCombinations(): List<String> = listOf(string)

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