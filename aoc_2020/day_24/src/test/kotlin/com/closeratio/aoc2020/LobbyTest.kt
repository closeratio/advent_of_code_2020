package com.closeratio.aoc2020

import com.closeratio.aoc2020.Colour.BLACK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class LobbyTest {

    @Test
    fun flipTiles() {
        val result = Lobby().flipTiles(
            DirectionList.parse(javaClass.getResource("/test_input_1.txt").readText())
        )

        assertThat(result.filter { it.colour == BLACK }, hasSize(10))
    }

}