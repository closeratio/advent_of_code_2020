package com.closeratio.aoc2020

data class Ticket(
    val values: List<Long>
) {

    fun getInvalidValues(fieldRules: List<FieldRule>): List<Long> = values
        .filter { value ->
            !fieldRules.any { it.isFieldValid(value) }
        }

}