package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

abstract class Seat(
    val position: Vec2i
) {

    abstract fun nextStateSimple(applicableSeats: List<Seat>): Seat
    abstract fun nextStateComplex(applicableSeats: List<Seat>): Seat

    fun getAdjacentSeats(seatMap: Map<Vec2i, Seat>): Set<Vec2i> = position
        .adjacent()
        .mapNotNull { seatMap[it]?.position }
        .toSet()

    fun getVisibleSeats(seatMap: Map<Vec2i, Seat>): Set<Vec2i> {
        val visibleCandidates = seatMap
            .values
            .filter { seat ->
                if (seat.position.x == position.x || seat.position.y == position.y) {
                    true
                } else (seat.position - position).abs().let { it.x == it.y }
            }
            .map { it.position }

        val groupedCandidates: List<List<Vec2i>> = listOf(
            visibleCandidates.filter { it.x == position.x && it.y < position.y }, // Above
            visibleCandidates.filter { it.x == position.x && it.y > position.y }, // Below
            visibleCandidates.filter { it.y == position.y && it.x < position.x }, // Left
            visibleCandidates.filter { it.y == position.y && it.x > position.x }, // Right

            visibleCandidates.filter { it.x < position.x && it.y < position.y }, // Diagonal up-left
            visibleCandidates.filter { it.x > position.x && it.y < position.y }, // Diagonal up-right
            visibleCandidates.filter { it.x < position.x && it.y > position.y }, // Diagonal down-left
            visibleCandidates.filter { it.x > position.x && it.y > position.y } // Diagonal down-right
        )

        val finalCandidates = groupedCandidates.mapNotNull { sublist ->
            sublist.sortedBy { it.manhattan(position) }.firstOrNull()
        }

        return finalCandidates.toSet()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Seat

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(position=$position)"
    }

}