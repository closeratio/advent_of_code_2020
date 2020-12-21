package com.closeratio.aoc2020

data class Food(
    val ingredients: Set<Ingredient>,
    val allergens: Set<Allergen>
) {

    companion object {
        fun from(input: String): List<Food> = input
            .trim()
            .split("\n")
            .map { it.trim() }
            .map { line ->
                val (ingredientChunk, allergenChunk) = line
                    .split(" (contains ")
                    .map { it.trim(')') }

                Food(
                    ingredientChunk.split(" ").map { Ingredient(it) }.toSet(),
                    allergenChunk.split(", ").map { Allergen(it) }.toSet()
                )
            }
    }

}