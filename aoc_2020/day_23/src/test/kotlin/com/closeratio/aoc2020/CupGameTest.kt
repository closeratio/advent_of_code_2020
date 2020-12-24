package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class CupGameTest {

    val game = CupGame("389125467")

    @Test
    fun iterate10() {
        val result = game.iterate(10)

        assertThat(result, `is`("92658374"))
    }

    @Test
    fun iterate100() {
        val result = game.iterate(100)

        assertThat(result, `is`("67384529"))
    }

}