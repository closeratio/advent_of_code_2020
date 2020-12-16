package com.closeratio.aoc2020

data class FieldRule(
    val name: String,
    val validRanges: List<LongRange>
) {

    fun isFieldValid(
        value: Long
    ): Boolean = validRanges.any { value in it }

}