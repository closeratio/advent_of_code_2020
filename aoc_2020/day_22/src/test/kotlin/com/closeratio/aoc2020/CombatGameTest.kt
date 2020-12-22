package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class CombatGameTest {

    private val game = CombatGame.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
        assertThat(
            game.initialState, `is`(
                listOf(
                    Deck(
                        "Player 1", listOf(
                            Card(9), Card(2), Card(6), Card(3), Card(1)
                        )
                    ),
                    Deck(
                        "Player 2", listOf(
                            Card(5), Card(8), Card(4), Card(7), Card(10)
                        )
                    )
                )
            )
        )
    }

    @Test
    fun computeWinningScore() {
        val result = game.computeWinningScore()

        assertThat(result, `is`(306L))
    }

}