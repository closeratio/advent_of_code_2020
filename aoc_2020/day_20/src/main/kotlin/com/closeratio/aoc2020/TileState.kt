package com.closeratio.aoc2020

data class TileState(
    val id: Long,
    val pixels: List<List<Char>>
) {

    val size = pixels.first().size

    val topEdge: String = pixels.first().joinToString("")
    val bottomEdge: String = pixels.last().joinToString("")
    val leftEdge: String = pixels.map { it.first() }.joinToString("")
    val rightEdge: String = pixels.map { it.last() }.joinToString("")

    fun rotate(): TileState = (1..size)
        .map { column ->
            (1..size).reversed().map { row ->
                pixels[row - 1][column - 1]
            }
        }
        .let {
            TileState(id, it)
        }

    fun flip(): TileState = TileState(
        id,
        pixels.map { it.reversed() }
    )

}