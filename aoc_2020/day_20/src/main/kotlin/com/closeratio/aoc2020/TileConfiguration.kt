package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i
import kotlin.math.pow
import kotlin.math.roundToInt

data class TileConfiguration(
    val tiles: List<List<TileState>>
) {

    fun checksum(): Long = listOf(
        tiles.first().first().id,
        tiles.first().last().id,
        tiles.last().first().id,
        tiles.last().last().id
    ).reduce { acc, curr -> acc * curr }

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
