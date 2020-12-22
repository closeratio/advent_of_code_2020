package com.closeratio.aoc2020

class CombatGame(
    val initialState: List<Deck>
) {

    fun computeWinningScore(): Long {
        val totalCardCount = initialState.map { it.cards.size }.sum()

        val mutableDecks = initialState
            .map {
                Deck(
                    it.owner,
                    ArrayList(it.cards)
                )
            }

        // Keep iterating until one deck has all the cards and all the others are empty
        while (mutableDecks.any { it.cards.isNotEmpty() && it.cards.size != totalCardCount }) {
            val roundCards = mutableDecks
                .map {
                    val card = (it.cards as MutableList).removeAt(0)
                    card to it
                }
                // Sort descending so that the first card in the list is the winning one
                .sortedByDescending { it.first.value }

            (roundCards.first().second.cards as MutableList).addAll(roundCards.map { it.first })
        }

        val winningDeck = mutableDecks.find { it.cards.isNotEmpty() }!!

        return winningDeck
            .cards
            .reversed()
            .mapIndexed { index, card -> (index + 1) * card.value }
            .sum()
    }

    companion object {

        fun from(input: String): CombatGame = input
            .trim()
            .split("\n\n")
            .map { it.trim().split("\n") }
            .map { deckChunk ->
                val owner = deckChunk.first().trim(':')
                val cardLines = deckChunk.drop(1)

                Deck(
                    owner,
                    cardLines.map { Card(it.toLong()) }
                )
            }
            .let {
                CombatGame(it)
            }

    }

}