package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i
import kotlin.math.pow
import kotlin.math.roundToInt

data class TileConfiguration(
    val tiles: List<List<TileState>>
) {

    private val size = tiles.size

    private val combinedPixels: List<List<Char>> = let {
        val trimmedTiles = tiles
            .map { row ->
                row.map { state ->
                    state.pixels
                        .drop(1)
                        .dropLast(1)
                        .map { it.drop(1).dropLast(1) }
                }
            }

        trimmedTiles.flatMap { row ->
            val chars = arrayListOf<ArrayList<Char>>()
            repeat(trimmedTiles.first().first().size) { rowIndex ->
                chars += arrayListOf<Char>()

                row.forEach { tile ->
                    chars.last() += tile[rowIndex]
                }
            }
            chars
        }
    }

    fun checksum(): Long = listOf(
        tiles.first().first().id,
        tiles.first().last().id,
        tiles.last().first().id,
        tiles.last().last().id
    ).reduce { acc, curr -> acc * curr }

    private fun rotate(): TileConfiguration = (1..size)
        .map { column ->
            (1..size).reversed().map { row ->
                tiles[row - 1][column - 1].rotate()
            }
        }
        .let {
            TileConfiguration(it)
        }

    private fun flip(): TileConfiguration = TileConfiguration(
        tiles.map { row ->
            row.reversed().map {
                it.flip()
            }
        }
    )

    fun generateImage(): String = combinedPixels
        .joinToString("\n") { line ->
            line.joinToString("")
        }

    fun permutations(): List<TileConfiguration> = listOf(
        this,
        rotate(),
        rotate().rotate(),
        rotate().rotate().rotate(),
        flip(),
        flip().rotate(),
        flip().rotate().rotate(),
        flip().rotate().rotate().rotate()
    )

    fun calculateWaterRoughness(
        searchImage: SearchImage
    ): Int = permutations()
        .asSequence()
        .map { searchImage.findTarget(combinedPixels) }
        .filter { it > 0 }
        .first()
        .let { monsterPixelCount ->
            combinedPixels.flatten().filter { it == '#' }.size - monsterPixelCount
        }

    companion object {

        fun computeFrom(tiles: Set<Tile>): TileConfiguration {
            val width = tiles.size.toDouble().pow(0.5)
            if (width - width.roundToInt().toDouble() > 0.0001) {
                throw IllegalArgumentException("Number of tiles does not produce a square: ${tiles.size}")
            }

            val allStates = tiles.flatMap { it.states }

            val startingCandidates = allStates
                .filter { state ->
                    allStates
                        .filter { it.id != state.id }
                        .none { it.bottomEdge == state.topEdge }
                }
                .filter { state ->
                    allStates
                        .filter { it.id != state.id }
                        .none { it.rightEdge == state.leftEdge }
                }

            return startingCandidates
                .asSequence()
                .mapNotNull { candidate ->
                    computeConfiguration(
                        width.roundToInt(),
                        listOf(listOf(candidate)),
                        allStates
                    )
                }
                .firstOrNull()
                ?: throw IllegalStateException("No tile state configurations could be found")
        }

        private fun computeConfiguration(
            width: Int,
            currentConfiguration: List<List<TileState>>,
            allStates: List<TileState>
        ): TileConfiguration? {

            if (currentConfiguration.flatten().size == width * width) {
                return TileConfiguration(
                    currentConfiguration
                )
            }

            val nextTilePos: Vec2i = if (currentConfiguration.last().size == width) {
                Vec2i(0, currentConfiguration.size)
            } else {
                Vec2i(currentConfiguration.last().size, currentConfiguration.size - 1)
            }

            val leftTileState: TileState? = if (nextTilePos.x > 0) {
                currentConfiguration.last().last()
            } else {
                null
            }

            val topTileState: TileState? = if (nextTilePos.y > 0) {
                currentConfiguration[nextTilePos.y - 1][nextTilePos.x]
            } else {
                null
            }

            if (leftTileState == null && topTileState == null) {
                throw IllegalStateException("Couldn't find adjacent tiles to match with")
            }

            val exhaustedIds = currentConfiguration.flatten().map { it.id }.toSet()
            val availableStates = allStates.filter { it.id !in exhaustedIds }

            val candidateStates = availableStates
                .filter { state ->
                    if (leftTileState != null) {
                        leftTileState.rightEdge == state.leftEdge
                    } else {
                        true
                    }
                }
                .filter { state ->
                    if (topTileState != null) {
                        topTileState.bottomEdge == state.topEdge
                    } else {
                        true
                    }
                }

            return candidateStates
                .mapNotNull { candidateState ->
                    val candidateConfig: List<List<TileState>> = if (leftTileState != null) {
                        currentConfiguration.dropLast(1) + listOf(currentConfiguration.last() + candidateState)
                    } else {
                        currentConfiguration + listOf(listOf(candidateState))
                    }

                    computeConfiguration(
                        width,
                        candidateConfig,
                        allStates
                    )
                }
                .firstOrNull()
        }

    }

}
