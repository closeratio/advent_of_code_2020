package com.closeratio.aoc2020.math

data class Vec4i(
    val x: Int,
    val y: Int,
    val z: Int,
    val w: Int
): Vec {

    override fun adjacent(): Set<Vec4i> = ((x - 1)..(x + 1))
        .flatMap { x ->
            ((y - 1)..(y + 1)).flatMap { y ->
                ((z - 1)..(z + 1)).flatMap { z ->
                    ((w - 1)..(w + 1)).map { w ->
                        Vec4i(x, y, z, w)
                    }
                }
            }
        }
        .filter { it != this }
        .toSet()

    operator fun plus(other: Vec4i): Vec4i = Vec4i(
        x + other.x,
        y + other.y,
        z + other.z,
        w + other.w
    )

    operator fun minus(other: Vec4i): Vec4i = Vec4i(
        x - other.x,
        y - other.y,
        z - other.z,
        w - other.w
    )

    companion object {
        val ZERO = Vec4i(0, 0, 0, 0)
    }

}
