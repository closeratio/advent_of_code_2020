package com.closeratio.aoc2020.math

import kotlin.math.absoluteValue

data class Vec2i(
    val x: Int,
    val y: Int
) {

    fun adjacent(): Set<Vec2i> = setOf(
        up(),
        up().right(),
        right(),
        down().right(),
        down(),
        down().left(),
        left(),
        up().left()
    )

    fun up(): Vec2i = Vec2i(x, y - 1)
    fun right(): Vec2i = Vec2i(x + 1, y)
    fun down(): Vec2i = Vec2i(x, y + 1)
    fun left(): Vec2i = Vec2i(x - 1, y)

    operator fun plus(other: Vec2i): Vec2i = Vec2i(
        x + other.x,
        y + other.y
    )

    operator fun minus(other: Vec2i): Vec2i = Vec2i(
        x - other.x,
        y - other.y
    )

    fun abs(): Vec2i = Vec2i(
        x.absoluteValue,
        y.absoluteValue
    )

    fun manhattan(other: Vec2i): Int = (other - this).abs().let { it.x + it.y }

    companion object {
        val ZERO = Vec2i(0, 0)
    }

}
