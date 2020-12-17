package com.closeratio.aoc2020

data class Ticket(
    val values: List<Long>
) {

    fun isInvalid(fieldRules: List<FieldRule>): Boolean = values
        .any { value ->
            !fieldRules.any { it.isFieldValid(value) }
        }

    fun getInvalidValues(fieldRules: List<FieldRule>): List<Long> = values
        .filter { value ->
            !fieldRules.any { it.isFieldValid(value) }
        }

    fun getValidIndices(fieldRule: FieldRule): Set<Int> = values
        .mapIndexed { index, fieldValue -> index to fieldValue }
        .filter { (_, fieldValue) -> fieldRule.isFieldValid(fieldValue) }
        .map { (index, _) -> index }
        .toSet()

}