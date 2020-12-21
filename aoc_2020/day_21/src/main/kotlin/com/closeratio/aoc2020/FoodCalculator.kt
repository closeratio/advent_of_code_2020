package com.closeratio.aoc2020

class FoodCalculator(
    private val foods: List<Food>
) {

    private fun calculateSafeIngredients(): Set<Ingredient> {
        val allergenFoodMap = foods
            .flatMap { food -> food.allergens.map { it to food } }
            .groupBy({ it.first }) { it.second }

        val unsafeIngredients = allergenFoodMap
            .flatMap { (_, foods) ->
                foods
                    .flatMap { it.ingredients }
                    .toSet()
                    .filter { ingredient -> foods.all { ingredient in it.ingredients } }
            }
            .toSet()

        return foods
            .flatMap { it.ingredients }
            .toSet()
            .filter { it !in unsafeIngredients }
            .toSet()
    }

    fun calculateSafeIngredientCount(): Int = calculateSafeIngredients()
        .map { ingredient ->
            foods.filter { ingredient in it.ingredients }.size
        }
        .sum()

    fun calculateCanonicalDangerousIngredientList(): String {
        val safeIngredients = calculateSafeIngredients()

        val allergenIngredientMap = foods
            .flatMap { food -> food.allergens.map { it to food.ingredients } }
            .groupBy({ it.first }) { it.second }
            .mapValues { (_, ingredients) ->
                ingredients.flatten().filter { it !in safeIngredients }.toSet()
            }

        val unmappedAllergens = foods
            .flatMap { it.allergens }
            .toHashSet()
        val mappedAllergens = hashMapOf<Allergen, Ingredient>()
        val mappedIngredients = hashSetOf<Ingredient>()

        // Keep iterating while there are allergens left to map to ingredients
        while (unmappedAllergens.isNotEmpty()) {
            val (allergen, ingredient) = unmappedAllergens
                .asSequence() // Convert to sequence so we stop as soon as we've mapped an allergen
                .map { allergen ->
                    // Get ingredients mentioned in the same list as the allergen which have not already been mapped to
                    // another allergen
                    val ingredients = allergenIngredientMap
                        .getValue(allergen)
                        .filter { it !in mappedIngredients }
                    allergen to ingredients
                }
                .mapNotNull { (allergen, ingredients) ->
                    // Find a set of "candidate ingredients". These are ingredients which are always mentioned wherever
                    // the allergen is mentioned.
                    val candidateIngredients = ingredients
                        .filter { ingredient ->
                            foods.filter { allergen in it.allergens }.all { ingredient in it.ingredients }
                        }

                    // If there's only one candidate ingredient which appears alongside this allergen, we've effectively
                    // done a process of elimination and this ingredient must map to the allergen. Otherwise we don't
                    // have enough information (at this point) to say which ingredient maps to this allergen. We always
                    // expect there to be at least one candidate ingredient for an allergen. If we've not found any,
                    // this suggests the process of elimination has not worked properly so we should throw an exception.
                    when {
                        candidateIngredients.size == 1 -> allergen to candidateIngredients.first()
                        candidateIngredients.isEmpty() ->
                            throw IllegalStateException("No candidate ingredients available for $allergen")
                        else -> null
                    }
                }
                .firstOrNull() ?: throw IllegalStateException("Unable to calculate next allergen to ingredient mapping")

            unmappedAllergens.remove(allergen)
            mappedAllergens[allergen] = ingredient
            mappedIngredients += ingredient
        }

        return mappedAllergens
            .entries
            .sortedBy { it.key.value }
            .joinToString(",") { it.value.value }
    }

}