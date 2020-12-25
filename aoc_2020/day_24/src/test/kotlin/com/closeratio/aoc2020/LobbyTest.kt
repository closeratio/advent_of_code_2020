package com.closeratio.aoc2020

import com.closeratio.aoc2020.Colour.BLACK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LobbyTest {

    @Test
    fun flipTiles() {
        val result = Lobby().flipTiles(
            DirectionList.parse(javaClass.getResource("/test_input_1.txt").readText())
        )

        assertThat(result.filter { it.colour == BLACK }, hasSize(10))
    }

    @ParameterizedTest
    @MethodSource("getEvolveParameters")
    fun evolve(
        dayCount: Int,
        expectedBlackTiles: Int
    ) {
        val result = Lobby()
            .apply {
                flipTiles(
                    DirectionList.parse(javaClass.getResource("/test_input_1.txt").readText())
                )
            }
            .evolve(dayCount)


        assertThat(result.filter { it.colour == BLACK }, hasSize(expectedBlackTiles))
    }

    companion object {

        @JvmStatic
        fun getEvolveParameters(): List<Arguments> = listOf(
            Arguments.of(1, 15),
            Arguments.of(2, 12),
            Arguments.of(3, 25),
            Arguments.of(4, 14),
            Arguments.of(5, 23),
            Arguments.of(10, 37),
            Arguments.of(100, 2208)
        )

    }

}