package com.closeratio.aoc2020

import com.closeratio.aoc2020.Colour.BLACK
import com.closeratio.aoc2020.Colour.WHITE
import com.closeratio.aoc2020.math.Vec2i
import com.closeratio.aoc2020.math.Vec2i.Companion.ZERO

class Lobby {

    private val tileMap = hashMapOf<Vec2i, Tile>()

    fun flipTiles(
        directions: List<DirectionList>
    ): Set<Tile> {
        directions.forEach { directionList ->
            val tile = findTile(directionList)
            tileMap[tile.position] = tile.flip()
        }

        return tileMap.values.toSet()
    }

    private fun findTile(
        directionList: DirectionList
    ): Tile {
        var currPos = ZERO
        tileMap.getOrPut(ZERO) { Tile(ZERO, WHITE) }

        directionList.directions.forEach { direction ->
            val nextPos = tileMap.getValue(currPos).getPosition(direction)

            if (nextPos !in tileMap) {
                tileMap[nextPos] = Tile(nextPos, WHITE)
            }

            currPos = nextPos
        }

        return tileMap.getValue(currPos)
    }

    fun evolve(
        dayCount: Int
    ): Set<Tile> {
        (1..dayCount).map {
            // Evolve tiles
            tileMap
                .values
                .flatMap { it.adjacent() }
                .toSet()
                .map { evolveTile(it) }
                .forEach { tile -> tileMap[tile.position] = tile }
        }

        return tileMap.values.toSet()
    }

    private fun evolveTile(
        position: Vec2i
    ): Tile {
        val tile = tileMap.getOrDefault(position, Tile(position, WHITE))

        val blackCount = tile
            .adjacent()
            .map { tilePos -> tileMap.getOrDefault(tilePos, Tile(tilePos, WHITE)) }
            .count { it.colour == BLACK }

        return when (tile.colour) {
            WHITE -> {
                if (blackCount == 2) tile.flip() else tile
            }
            BLACK -> {
                if (blackCount == 0 || blackCount > 2) tile.flip() else tile
            }
        }
    }

}

