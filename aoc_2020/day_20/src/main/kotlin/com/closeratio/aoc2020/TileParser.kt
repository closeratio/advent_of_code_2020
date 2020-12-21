package com.closeratio.aoc2020

object TileParser {

    fun parseTiles(input: String): Set<Tile> = input
        .trim()
        .split("\n\n")
        .map { parseTile(it) }
        .toSet()

    private fun parseTile(input: String): Tile {
        val lines = input
            .split("\n")

        val id = lines
            .first()
            .let { """\d+""".toRegex().find(it)!!.groupValues.first() }
            .toLong()

        return Tile(
            id,
            lines
                .drop(1)
                .map { line -> line.map { it } }
                .let {
                    val initialState = TileState(id, it)
                    val flipped = initialState.flip()

                    setOf(
                        initialState,
                        initialState.rotate(),
                        initialState.rotate().rotate(),
                        initialState.rotate().rotate().rotate(),
                        flipped,
                        flipped.rotate(),
                        flipped.rotate().rotate(),
                        flipped.rotate().rotate().rotate()
                    )
                }
        )
    }

}