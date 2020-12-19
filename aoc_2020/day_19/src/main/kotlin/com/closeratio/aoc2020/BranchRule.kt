package com.closeratio.aoc2020

class BranchRule(
    id: Int,
    val combinations: List<List<Rule>>
) : Rule(id) {

    override fun getValidStringCombinations(): List<String> = combinations.flatMap { combination ->
        val stringGroups: List<List<String>> = combination.map { it.getValidStringCombinations() }

        stringGroups
            .first()
            .flatMap {
                enumerateCombinations(it, stringGroups.drop(1))
            }
    }

    private fun enumerateCombinations(
        currString: String,
        combinations: List<List<String>>
    ): List<String> = if (combinations.isEmpty()) {
        listOf(currString)
    } else {
        combinations
            .first()
            .flatMap {
                enumerateCombinations(currString + it, combinations.drop(1))
            }
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