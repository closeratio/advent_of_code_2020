package com.closeratio.aoc2020

data class PasswordRulePair(
    val rule: Rule,
    val password: String
) {

    fun isValid() = password
        .groupBy({ it }) { 1 }
        .mapValues { (_, value) -> value.sum() }
        .getOrDefault(rule.character, 0)
        .let {
            it >= rule.lowerCount && it <= rule.upperCount
        }

}