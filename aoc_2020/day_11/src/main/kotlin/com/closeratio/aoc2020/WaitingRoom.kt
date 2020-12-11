package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

class WaitingRoom(
    val seats: Map<Vec2i, Seat>,
    seatCacheSimple: Map<Vec2i, Set<Vec2i>>? = null,
    seatCacheComplex: Map<Vec2i, Set<Vec2i>>? = null
) {

    private val applicableSeatCacheSimple: Map<Vec2i, Set<Vec2i>> = seatCacheSimple ?: seats
        .values
        .associate { it.position to it.getAdjacentSeats(seats) }

    private val applicableSeatCacheComplex: Map<Vec2i, Set<Vec2i>> = seatCacheComplex ?: seats
        .values
        .associate { it.position to it.getVisibleSeats(seats) }

    private fun iterate(
        seatTransform: (Seat) -> Seat,
    ): WaitingRoom = WaitingRoom(
        seats
            .map { (position, seat) -> position to seatTransform(seat) }
            .toMap(),
        applicableSeatCacheSimple,
        applicableSeatCacheComplex
    )

    fun iterateSimple(): WaitingRoom = iterate { seat ->
        seat.nextStateSimple(applicableSeatCacheSimple.getValue(seat.position).map { seats.getValue(it) })
    }

    fun iterateComplex(): WaitingRoom = iterate { seat ->
        seat.nextStateComplex(applicableSeatCacheComplex.getValue(seat.position).map { seats.getValue(it) })
    }

    private fun iterateUntilStable(
        iteration: WaitingRoom.() -> WaitingRoom
    ): Int {
        val states = arrayListOf(this, this.iteration())

        while (states.last() != states[states.size - 2]) {
            states += states.last().iteration()
        }

        return states.last()
            .seats
            .values
            .filterIsInstance<OccupiedSeat>()
            .size
    }

    fun iterateUntilStableSimple(): Int = iterateUntilStable { iterateSimple() }

    fun iterateUntilStableComplex(): Int = iterateUntilStable { iterateComplex() }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WaitingRoom) return false

        if (seats != other.seats) return false

        return true
    }

    override fun hashCode(): Int {
        return seats.hashCode()
    }

    override fun toString(): String {
        return "WaitingRoom(${seats.values.filterIsInstance<OccupiedSeat>().size} occupied, " +
                "${seats.values.filterIsInstance<EmptySeat>().size} empty)"
    }


    companion object {

        fun from(input: String): WaitingRoom = WaitingRoom(input
            .trim()
            .split("\n")
            .flatMapIndexed { y, line ->
                line.trim().mapIndexedNotNull { x, char ->
                    val vec = Vec2i(x, y)
                    when (char) {
                        'L' -> vec to EmptySeat(vec)
                        '#' -> vec to OccupiedSeat(vec)
                        else -> null
                    }
                }
            }
            .toMap()
        )

    }

}