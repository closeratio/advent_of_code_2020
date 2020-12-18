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

    companion object {

        @JvmStatic
        fun getParseArguments(): List<Arguments> = listOf(
            Arguments.of(
                "2 * 3 + (4 * 5)", WrappedExpression(
                    listOf(
                        Number(2),
                        Times(),
                        Number(3),
                        Plus(),
                        WrappedExpression(
                            listOf(
                                Number(4),
                                Times(),
                                Number(5)
                            )
                        )
                    )
                )
            ),
            Arguments.of(
                "5 + (8 * 3 + 9 + 3 * 4 * 3)", WrappedExpression(
                    listOf(
                        Number(5),
                        Plus(),
                        WrappedExpression(
                            listOf(
                                Number(8),
                                Times(),
                                Number(3),
                                Plus(),
                                Number(9),
                                Plus(),
                                Number(3),
                                Times(),
                                Number(4),
                                Times(),
                                Number(3)
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

    }

}