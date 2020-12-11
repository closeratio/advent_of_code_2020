package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

class OccupiedSeat(
    position: Vec2i
) : Seat(position) {

    override fun nextStateSimple(applicableSeats: List<Seat>): Seat = if (
        applicableSeats.filterIsInstance<OccupiedSeat>().size >= 4
    ) {
        EmptySeat(position)
    } else {
        this
    }

    override fun nextStateComplex(applicableSeats: List<Seat>): Seat = if (
        applicableSeats.filterIsInstance<OccupiedSeat>().size >= 5
    ) {
        EmptySeat(position)
    } else {
        this
    }
}