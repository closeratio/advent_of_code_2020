package com.closeratio.aoc2020

data class WrappedExpression(
    val elements: List<Element>
) : Element() {

    fun evaluate(): Long = elements.fold(Pair<Long, Element>(0L, Plus())) { (total, lastOperation), curr ->
        val value = when (curr) {
            is NumberExpression -> curr.value
            is WrappedExpression -> curr.evaluate()
            else -> 0L
        }

        val newTotal: Long = if (curr is NumberExpression || curr is WrappedExpression) {
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

    fun evaluateAdvanced(): Long {
        val currElements = elements.toMutableList()

        while (currElements.contains(Plus())) {
            // Calculate the result of adding the two values
            val replacement = evaluateNextPlus(currElements)

            // Remove the old values
            val plusIndex = currElements.indexOf(Plus())
            repeat(3) {
                currElements.removeAt(plusIndex - 1)
            }

            // Add the replacement
            currElements.add(plusIndex - 1, replacement)
        }

        return currElements
            .map { getExpressionAdvancedValue(it, 1L) }
            .reduce { acc, curr -> acc * curr }
    }

    private fun evaluateNextPlus(
        currElements: MutableList<Element>
    ): NumberExpression {
        val plusIndex = currElements.indexOf(Plus())

        val leftValue = getExpressionAdvancedValue(currElements[plusIndex - 1], 0L)
        val rightValue = getExpressionAdvancedValue(currElements[plusIndex + 1], 0L)

        return NumberExpression(leftValue + rightValue)
    }

    private fun getExpressionAdvancedValue(
        element: Element,
        default: Long
    ): Long = when (element) {
        is NumberExpression -> element.value
        is WrappedExpression -> element.evaluateAdvanced()
        else -> default
    }

}