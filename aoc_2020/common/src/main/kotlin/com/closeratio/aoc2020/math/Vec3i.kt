package com.closeratio.aoc2020.math

data class Vec3i(
    val x: Int,
    val y: Int,
    val z: Int
): Vec {

    override fun adjacent(): Set<Vec3i> = ((x - 1)..(x + 1))
        .flatMap { x ->
            ((y - 1)..(y + 1)).flatMap { y ->
                ((z - 1)..(z + 1)).map { z ->
                    Vec3i(x, y, z)
                }
            }
        }
        .filter { it != this }
        .toSet()

    operator fun plus(other: Vec3i): Vec3i = Vec3i(
        x + other.x,
        y + other.y,
        z + other.z
    )

    operator fun minus(other: Vec3i): Vec3i = Vec3i(
        x - other.x,
        y - other.y,
        z - other.z
    )

    companion object {
        val ZERO = Vec3i(0, 0, 0)
    }

}
