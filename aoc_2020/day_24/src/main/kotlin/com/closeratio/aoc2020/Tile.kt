package com.closeratio.aoc2020

import com.closeratio.aoc2020.Colour.BLACK
import com.closeratio.aoc2020.Colour.WHITE
import com.closeratio.aoc2020.math.Vec2i

data class Tile(
    val position: Vec2i,
    val colour: Colour
) {

    fun east(): Vec2i = position + Vec2i(2, 0)
    fun west(): Vec2i = position + Vec2i(-2, 0)

    fun northEast(): Vec2i = position + Vec2i(1, -1)
    fun northWest(): Vec2i = position + Vec2i(-1, -1)

    fun southEast(): Vec2i = position + Vec2i(1, 1)
    fun southWest(): Vec2i = position + Vec2i(-1, 1)

    fun flip(): Tile = Tile(
        position, when (colour) {
            BLACK -> WHITE
            WHITE -> BLACK
        }
    )

    fun getPosition(direction: Direction): Vec2i = when (direction) {
        Direction.EAST -> east()
        Direction.WEST -> west()
        Direction.NORTHEAST -> northEast()
        Direction.NORTHWEST -> northWest()
        Direction.SOUTHEAST -> southEast()
        Direction.SOUTHWEST -> southWest()
    }

}

