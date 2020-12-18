package com.closeratio.aoc2020

data class WrappedExpression(
    val elements: List<Element>
): Element() {

    fun evaluate(): Long = elements.fold(Pair<Long, Element>(0L, Plus())) { (total, lastOperation), curr ->
        val value = when (curr) {
            is Number -> curr.value
            is WrappedExpression -> curr.evaluate()
            else -> 0L
        }

        val newTotal: Long = if (curr is Number || curr is WrappedExpression) {
            when (lastOperation) {
                is Plus -> total + value
                is Times -> total * value
                else -> throw IllegalStateException("Unhandled operation: $lastOperation")
            }
        } else {
            total
        }

        val newOperation = when (curr) {
            is Plus, is Times -> curr
            else -> lastOperation
        }

        newTotal to newOperation
    }.first

}