package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FoodCalculatorTest {

    val calculator = FoodCalculator(Food.from(javaClass.getResource("/test_input_1.txt").readText()))

    @Test
    fun calculateSafeIngredientCount() {
        val result = calculator.calculateSafeIngredientCount()

        assertThat(result, `is`(5))
    }

    @Test
    fun calculateCanonicalDangerousIngredientList() {
        val result = calculator.calculateCanonicalDangerousIngredientList()

        assertThat(
            result,
            `is`("mxmxvkd,sqjhc,fvjkl")
        )
    }

}