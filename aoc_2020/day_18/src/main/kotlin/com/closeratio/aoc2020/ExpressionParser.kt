package com.closeratio.aoc2020

object ExpressionParser {

    fun parse(input: String): WrappedExpression = input
        .trim()
        .let {
            parseWrappedExpression(it)
        }

    private fun parseWrappedExpression(input: String): WrappedExpression {
        val elements = arrayListOf<Element>()

        var currIndex = 0
        while (currIndex < input.length) {
            val currChar = input[currIndex]
            when {
                currChar.isDigit() -> elements += Number(currChar.toString().toLong())
                currChar == '+' -> elements += Plus()
                currChar == '*' -> elements += Times()
                currChar == '(' -> {

                    val substringStart = currIndex + 1
                    var bracketCount = 1
                    while (bracketCount > 0) {
                        currIndex++

                        if (input[currIndex] == '(') {
                            bracketCount++
                        } else if (input[currIndex] == ')') {
                            bracketCount--
                        }
                    }

                    val substringEnd = currIndex
                    val substring = input.substring(substringStart, substringEnd)
                    elements += parseWrappedExpression(substring)
                }
            }

            currIndex++
        }

        return WrappedExpression(elements)
    }

}

