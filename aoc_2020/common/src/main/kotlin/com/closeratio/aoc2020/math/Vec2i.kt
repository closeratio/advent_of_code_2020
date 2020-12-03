package com.closeratio.aoc2020.math

data class Vec2i(
    val x: Int,
    val y: Int
) {

    operator fun plus(other: Vec2i): Vec2i = Vec2i(
        x + other.x,
        y + other.y
    )

    companion object {
        val ZERO = Vec2i(0, 0)
    }

}
