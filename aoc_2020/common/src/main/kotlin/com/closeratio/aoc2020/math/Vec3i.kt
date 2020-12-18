package com.closeratio.aoc2020.math

import kotlin.math.absoluteValue

data class Vec3i(
    val x: Int,
    val y: Int,
    val z: Int
) {

    fun adjacent(): Set<Vec3i> = ((x - 1)..(x + 1))
        .flatMap { x ->
            ((y - 1)..(y + 1)).flatMap { y ->
                ((z - 1)..(z + 1)).map { z ->
                    Vec3i(x, y, z)
                }
            }
        }
        .filter { it != this }
        .toSet()

    fun up(): Vec3i = up(1)
    fun up(amount: Int): Vec3i = Vec3i(x, y - amount, z)

    fun right(): Vec3i = right(1)
    fun right(amount: Int): Vec3i = Vec3i(x + amount, y, z)

    fun down(): Vec3i = down(1)
    fun down(amount: Int): Vec3i = Vec3i(x, y + amount, z)

    fun left(): Vec3i = left(1)
    fun left(amount: Int): Vec3i = Vec3i(x - amount, y, z)

    fun `in`(): Vec3i = `in`(1)
    fun `in`(amount: Int): Vec3i = Vec3i(x, y, z + amount)

    fun out(): Vec3i = out(1)
    fun out(amount: Int): Vec3i = Vec3i(x, y, z - amount)

    fun withX(x: Int): Vec3i = Vec3i(x, y, z)
    fun withY(y: Int): Vec3i = Vec3i(x, y, z)
    fun withZ(z: Int): Vec3i = Vec3i(x, y, z)

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

    fun abs(): Vec3i = Vec3i(
        x.absoluteValue,
        y.absoluteValue,
        z.absoluteValue
    )

    fun manhattan(other: Vec3i): Int = (other - this).abs().let { it.x + it.y + it.z }
    fun manhattan(): Int = manhattan(ZERO)

    companion object {
        val ZERO = Vec3i(0, 0, 0)
    }

}
