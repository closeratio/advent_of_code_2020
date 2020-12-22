package com.closeratio.aoc2020

class CombatGame(
    val initialState: List<Deck>
) {

    fun computeWinningScoreSimple(): Long {
        val totalCardCount = initialState.map { it.cards.size }.sum()

        val states = arrayListOf(
            initialState
        )

        // Keep iterating until one deck has all the cards and all the others are empty
        while (states.last().any { it.cards.isNotEmpty() && it.cards.size != totalCardCount }) {
            val roundCards = states.last().map { it.drawCard() }

            val winner = roundCards.maxByOrNull { (card, _) -> card.value }!!.second.owner

            states += roundCards
                .map { (_, deck) ->
                    when (deck.owner) {
                        winner -> deck + roundCards.map { it.first }.sortedByDescending { it.value }
                        else -> deck
                    }
                }
        }

        val winningDeck = states.last().find { it.cards.isNotEmpty() }!!

        return winningDeck.cards.computeScore()
    }

    fun computeWinningScoreAdvanced(): Pair<String, Long> {
        val totalCardCount = initialState.map { it.cards.size }.sum()

        val states = linkedSetOf(
            initialState
        )

        var gameWinner: String? = null
        var winningScore: Long? = null

        // Keep iterating until one deck has all the cards and all the others are empty
        while (gameWinner == null) {
            // Each player takes the card out of the deck
            val roundCards = states.last().map { it.drawCard() }

            // If there's enough cards to recurse based on the value of the drawn cards, then recurse
            // Otherwise normal rules of combat apply
            val (winner, winningCardOrder) = if (roundCards.all { (card, deck) -> deck.cards.size >= card.value }) {
                val winner = CombatGame(
                    roundCards.map { (card, deck) ->
                        Deck(
                            deck.owner,
                            deck.cards.take(card.value.toInt())
                        )
                    }
                ).computeWinningScoreAdvanced().first

                val winningCard = roundCards.find { it.second.owner == winner }!!.first
                val remainingDeck = roundCards.filter { it.second.owner != winner }.map { it.first }

                winner to listOf(winningCard) + remainingDeck
            } else {
                val winner = roundCards.maxByOrNull { (card, _) -> card.value }!!.second.owner

                winner to roundCards.map { it.first }.sortedByDescending { it.value }
            }

            // Compute the new deck state
            val newDeckState = roundCards
                .map { (_, deck) ->
                    when (deck.owner) {
                        winner -> deck + winningCardOrder
                        else -> deck
                    }
                }

            val finished = newDeckState.all { it.cards.isEmpty() || it.cards.size == totalCardCount }

            when {
                 finished || newDeckState in states -> {
                    gameWinner = winner
                    winningScore = newDeckState
                        .find { it.owner == winner }!!
                        .cards
                        .computeScore()
                }
                else -> {
                    states += newDeckState
                }
            }
        }

        return gameWinner to winningScore!!
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

    private fun List<Card>.computeScore(): Long = reversed()
        .mapIndexed { index, card -> (index + 1) * card.value }
        .sum()

}