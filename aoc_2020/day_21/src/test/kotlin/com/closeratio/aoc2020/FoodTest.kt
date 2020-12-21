package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItem
import org.junit.jupiter.api.Test

class FoodTest {

    private val foods = Food.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
        assertThat(foods, hasSize(4))
        assertThat(foods, hasItem(
            Food(
                setOf(
                    Ingredient("mxmxvkd"),
                    Ingredient("kfcds"),
                    Ingredient("sqjhc"),
                    Ingredient("nhms")
                ),
                setOf(
                    Allergen("dairy"),
                    Allergen("fish")
                )
            )
        )
        )
    }

}