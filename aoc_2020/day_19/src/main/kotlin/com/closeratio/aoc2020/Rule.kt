package com.closeratio.aoc2020

abstract class Rule(
    val id: RuleId
) {

    abstract fun getMatchingSubstrings(
        ruleMap: Map<RuleId, Rule>,
        message: String,
        index: Int
    ): List<String>

}
