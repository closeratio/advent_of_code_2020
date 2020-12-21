package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TileStateTest {

    private val state = listOf(
        "..##.#..#.",
        "##..#.....",
        "#...##..#.",
        "####.#...#",
        "##.##.###.",
        "##...#.###",
        ".#.#.#..##",
        "..#....#..",
        "###...#.#.",
        "..###..###"
    ).map { line -> line.map { it } }
        .let { TileState(1, it) }

    @Test
    fun edges() {
        assertThat(state.topEdge, `is`("..##.#..#."))
        assertThat(state.bottomEdge, `is`("..###..###"))

        assertThat(state.leftEdge, `is`(".#####..#."))
        assertThat(state.rightEdge, `is`("...#.##..#"))
    }

    @Test
    fun rotation() {
        val rotated = state.rotate()

        assertThat(rotated.topEdge, `is`(".#..#####."))
        assertThat(rotated.bottomEdge, `is`("#..##.#..."))

        assertThat(rotated.leftEdge, `is`("..###..###"))
        assertThat(rotated.rightEdge, `is`("..##.#..#."))
    }

    @Test
    fun flipping() {
        val flipped = state.flip()

        assertThat(flipped.topEdge, `is`(".#..#.##.."))
        assertThat(flipped.bottomEdge, `is`("###..###.."))

        assertThat(flipped.rightEdge, `is`(".#####..#."))
        assertThat(flipped.leftEdge, `is`("...#.##..#"))
    }

}