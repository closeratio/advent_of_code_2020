package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ExpressionParserTest {

    @ParameterizedTest
    @MethodSource("getParseArguments")
    fun parse(
        input: String,
        expected: Element
    ) {

        val element = ExpressionParser.parse(input)

        assertThat(element, `is`(expected))

    }

    @ParameterizedTest
    @MethodSource("getEvaluateArguments")
    fun evaluate(
        input: String,
        expected: Long
    ) {

        val element = ExpressionParser.parse(input)
        val result = element.evaluate()

        assertThat(result, `is`(expected))

    }

    @ParameterizedTest
    @MethodSource("getEvaluateAdvancedArguments")
    fun evaluateAdvanced(
        input: String,
        expected: Long
    ) {

        val element = ExpressionParser.parse(input)
        val result = element.evaluateAdvanced()

        assertThat(result, `is`(expected))

    }

    companion object {

        @JvmStatic
        fun getParseArguments(): List<Arguments> = listOf(
            Arguments.of(
                "2 * 3 + (4 * 5)", WrappedExpression(
                    listOf(
                        NumberExpression(2),
                        Times(),
                        NumberExpression(3),
                        Plus(),
                        WrappedExpression(
                            listOf(
                                NumberExpression(4),
                                Times(),
                                NumberExpression(5)
                            )
                        )
                    )
                )
            ),
            Arguments.of(
                "5 + (8 * 3 + 9 + 3 * 4 * 3)", WrappedExpression(
                    listOf(
                        NumberExpression(5),
                        Plus(),
                        WrappedExpression(
                            listOf(
                                NumberExpression(8),
                                Times(),
                                NumberExpression(3),
                                Plus(),
                                NumberExpression(9),
                                Plus(),
                                NumberExpression(3),
                                Times(),
                                NumberExpression(4),
                                Times(),
                                NumberExpression(3)
                            )
                        )
                    )
                )
            )
        )

        @JvmStatic
        fun getEvaluateArguments(): List<Arguments> = listOf(
            Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L),
            Arguments.of("2 * 3 + (4 * 5)", 26L),
            Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437L),
            Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12_240L),
            Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13_632L)
        )

        @JvmStatic
        fun getEvaluateAdvancedArguments(): List<Arguments> = listOf(
            Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L),
            Arguments.of("2 * 3 + (4 * 5)", 46L),
            Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 1445L),
            Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 669_060L),
            Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 23_340L)
        )

    }

}