package com.closeratio.aoc2020

class CupGame(
    private val initialState: List<Cup>
) {

    private val orderedCupList = initialState.sortedBy { it.value }

    init {
        initialState.forEachIndexed { index, cup ->
            cup.next = initialState[(index + 1) % initialState.size]
        }
    }

    constructor(seed: String): this(
        seed.map { Cup(it.toString().toLong()) }
    )

    private fun iterate(
        moveCount: Int
    ) {
        var currentCup = initialState.first()

        repeat(moveCount) {

            // Get current cup, next 3 cups, and the one following so that refs can be updated
            val cupChunk = (1..4).runningFold(currentCup) { cup, _ -> cup.next }

            // Update first cup's ref to point to new next cup
            cupChunk.first().next = cupChunk.last()

            // Get cups that were taken
            val takenCups = cupChunk.drop(1).dropLast(1)
            val takenCupValues = takenCups.map { it.value }.toSet()

            // Find destination cup
            var destCupValue = currentCup.value - 1
            while (destCupValue in takenCupValues || destCupValue <= 0) {
                destCupValue--
                if (destCupValue <= 0) {
                    destCupValue = initialState.size.toLong()
                }
            }

            val destinationCup = orderedCupList[destCupValue.toInt() - 1]

            // Insert taken cups next to destination cup
            val cupNextToDestination = destinationCup.next
            destinationCup.next = takenCups.first()
            takenCups.last().next = cupNextToDestination

            // Update current cup
            currentCup = currentCup.next

        }
    }

    fun getFinalString(moveCount: Int): String {

        iterate(moveCount)

        // Take all cups after cup labeled "1"
        val startingCup = initialState.find { it.value == 1L }!!

        val result = (1 until initialState.size)
            .runningFold(startingCup) { acc, _ -> acc.next }
            .drop(1)

        return result.joinToString("") { it.value.toString() }
    }

    fun getNextNCups(
        moveCount: Int,
        targetCup: Long,
        nextCupCount: Int
    ): List<Cup> {

        iterate(moveCount)

        return (1..nextCupCount)
            .runningFold(orderedCupList[targetCup.toInt() - 1]) { curr, _ -> curr.next }
            .drop(1)
    }

}

