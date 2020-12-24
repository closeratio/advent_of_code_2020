package com.closeratio.aoc2020

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

}

