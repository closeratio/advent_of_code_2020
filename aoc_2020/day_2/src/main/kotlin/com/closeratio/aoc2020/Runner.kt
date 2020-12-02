package com.closeratio.aoc2020

object Runner: AbstractRunner<Int>() {

    override fun part1(): Int = PasswordRuleParser
        .parse(javaClass.getResource("/input.txt").readText())
        .filter { it.isValidOldRules() }
        .size

    override fun part2(): Int = PasswordRuleParser
        .parse(javaClass.getResource("/input.txt").readText())
        .filter { it.isValidCurrentRules() }
        .size

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}