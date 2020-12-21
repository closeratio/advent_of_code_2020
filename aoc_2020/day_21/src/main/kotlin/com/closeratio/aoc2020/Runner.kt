package com.closeratio.aoc2020

object Runner : AbstractRunner<String>() {

    private val foodCalculator = FoodCalculator(
        Food.from(javaClass.getResource("/input.txt").readText())
    )

    override fun part1(): String = foodCalculator.calculateSafeIngredientCount().toString()

    override fun part2(): String = foodCalculator.calculateCanonicalDangerousIngredientList()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}