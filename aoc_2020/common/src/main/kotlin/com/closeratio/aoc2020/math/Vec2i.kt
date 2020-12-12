package com.closeratio.aoc2020.math

import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

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

    fun up(): Vec2i = up(1)
    fun up(amount: Int): Vec2i = Vec2i(x, y - amount)

    fun right(): Vec2i = right(1)
    fun right(amount: Int): Vec2i = Vec2i(x + amount, y)

    fun down(): Vec2i = down(1)
    fun down(amount: Int): Vec2i = Vec2i(x, y + amount)

    fun left(): Vec2i = left(1)
    fun left(amount: Int): Vec2i = Vec2i(x - amount, y)

    fun rotate(radians: Double): Vec2i = Vec2i(
        (cos(radians) * x - sin(radians) * y).roundToInt(),
        (sin(radians) * x + cos(radians) * y).roundToInt()
    )

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
    fun manhattan(): Int = manhattan(ZERO)

    companion object {
        val ZERO = Vec2i(0, 0)
    }

}
