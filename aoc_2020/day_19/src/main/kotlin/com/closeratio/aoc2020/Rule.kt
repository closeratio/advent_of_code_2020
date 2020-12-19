package com.closeratio.aoc2020

abstract class Rule(
    val id: Int
) {

    abstract fun getValidStringCombinations(): List<String>

}
