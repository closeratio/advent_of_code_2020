package com.closeratio.aoc2020

data class PasswordRulePair(
    val rule: Rule,
    val password: String
) {

    fun isValidOldRules() = password
        .groupBy({ it }) { 1 }
        .mapValues { (_, value) -> value.sum() }
        .getOrDefault(rule.character, 0)
        .let {
            it >= rule.lower && it <= rule.upper
        }

    fun isValidCurrentRules(): Boolean = password
        .let {
            val firstChar = password[rule.lower - 1]
            val secondChar = password[rule.upper - 1]

            (firstChar == rule.character).xor(secondChar == rule.character)
        }

}