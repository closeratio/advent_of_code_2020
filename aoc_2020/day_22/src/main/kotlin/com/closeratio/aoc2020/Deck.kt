package com.closeratio.aoc2020

data class Deck(
    val owner: String,
    val cards: List<Card>
) {

    fun drawCard(): Pair<Card, Deck> = cards.first() to Deck(
        owner,
        cards.drop(1)
    )

    operator fun plus(cards: List<Card>): Deck = Deck(
        owner,
        this.cards + cards
    )

}