package com.closeratio.aoc2020

class CupGame(
    val seed: String
) {

    val initialState = seed.map { Cup(it.toString().toLong()) }

    fun iterate(
        moveCount: Int
    ): String {
        val cupList = initialState.toMutableList()
        var currentCup = cupList.first()

        repeat(moveCount) {
            val currCupIndex = cupList.indexOf(currentCup)

            // Take cups
            val takenCups = (1..3).map { offset ->
                cupList[(currCupIndex + offset) % cupList.size]
            }
            cupList.removeAll(takenCups)

            // Select destination cup
            var destinationCup = Cup(currentCup.value - 1)
            while (destinationCup in takenCups || destinationCup.value <= 0) {
                destinationCup = Cup(destinationCup.value - 1)
                if (destinationCup.value <= 0) {
                    destinationCup = initialState.maxByOrNull { it.value }!!
                }
            }

            // Insert taken cups
            val destinationCupIndex = cupList.indexOf(destinationCup)
            cupList.addAll(destinationCupIndex + 1, takenCups)

            // Current cup index might have changed after taken cups were re-inserted so have to find it again
            val updatedCurrCupIndex = cupList.indexOf(currentCup)
            currentCup = cupList[(updatedCurrCupIndex + 1) % cupList.size]
        }

        // Take all cups after cup labeled "1"
        val startIndex = cupList.indexOf(Cup(1L))

        return (1 until cupList.size)
            .map { offset ->
                cupList[(startIndex + offset) % cupList.size]
            }
            .joinToString("") {
                it.value.toString()
            }
    }

}

