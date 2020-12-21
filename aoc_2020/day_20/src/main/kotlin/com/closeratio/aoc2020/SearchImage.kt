package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

data class SearchImage(
    val searchPixels: Set<Vec2i>
) {

    private val reference = searchPixels.first()
    private val offsets = searchPixels.map { it - reference }

    fun findTarget(
        pixels: List<List<Char>>
    ): Int {
        val hashCharSet = pixels
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, character -> Vec2i(x, y) to character }
            }
            .filter { (_, character) -> character == '#' }
            .map { it.first }
            .toSet()

        return hashCharSet
            .fold(0) { count, charPos ->
                when {
                    offsets.all { offset -> (charPos + offset) in hashCharSet } -> count + searchPixels.size
                    else -> count
                }
            }
    }

    companion object {

        fun from(data: String): SearchImage = data
            .split("\n")
            .flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, character ->
                    when (character) {
                        '#' -> Vec2i(x, y)
                        else -> null
                    }
                }
            }
            .toSet()
            .let {
                SearchImage(it)
            }

    }

}
