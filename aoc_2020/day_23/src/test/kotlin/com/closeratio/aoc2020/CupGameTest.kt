package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class CupGameTest {

    private val game = CupGame("389125467")

    @Test
    fun getFinalString_10() {
        val result = game.getFinalString(10)

        assertThat(result, `is`("92658374"))
    }

    @Test
    fun getFinalString_100() {
        val result = game.getFinalString(100)

        assertThat(result, `is`("67384529"))
    }

    @Test
    fun getNextNCups() {
        val biggerGame = CupGame(
            "389125467".map {
                Cup(it.toString().toLong())
            } + (10..1_000_000).map {
                Cup(it.toLong())
            }
        )


        val result = biggerGame.getNextNCups(
            10_000_000,
            1L,
            2
        )

        assertThat(result, `is`(
            listOf(
                Cup(934_001),
                Cup(159_792)
            )
        ))
    }

}