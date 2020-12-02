package com.closeratio.aoc2020

object PasswordRuleParser {

    fun parse(input: String): List<PasswordRulePair> = input
        .split("\n")
        .map { it.trim() }
        .map { line ->
            val (rule, password) = line.split(":").map { it.trim() }
            val (limits, character) = rule.split(" ")
            val (lower, upper) = limits.split("-").map { it.toInt() }

            PasswordRulePair(
                Rule(
                    lower,
                    upper,
                    character.first()
                ),
                password
            )
        }

}

