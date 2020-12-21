package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class TileParseTest {

    private val tiles: Set<Tile> = TileParser.parseTiles(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun parse() {
        assertThat(tiles, hasSize(9))
        assertThat(
            tiles.map { it.id }.toSet(),
            hasItems(
                1951,
                2311,
                3079,
                2729,
                1427,
                2473,
                2971,
                1489,
                1171
            )
        )

        val tile = tiles.find { it.id == 2311L }!!
        val state = tile.states.first()

        assertThat(state.pixels.first(), `is`(
            "..##.#..#.".map { it }
        ))
    }

}