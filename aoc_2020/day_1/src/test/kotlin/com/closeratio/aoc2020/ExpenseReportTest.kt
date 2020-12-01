package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ExpenseReportTest {

    companion object {
        @JvmStatic
        fun generateInputs(): List<Arguments> = listOf(
                Arguments.of("/test_input_1.txt", 514579)
        )
    }

    @ParameterizedTest
    @MethodSource("generateInputs")
    fun testCalculatePairProduct(
            resourceName: String,
            expectedProduct: Long
    ) {
        val report: ExpenseReport = ExpenseReport.from(
                javaClass.getResource(resourceName).readText()
        )

        assertThat(report.calculatePairProduct(2020), `is`(expectedProduct))
    }

}