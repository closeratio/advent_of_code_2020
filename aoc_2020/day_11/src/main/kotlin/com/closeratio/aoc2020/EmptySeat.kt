package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i

class EmptySeat(
    position: Vec2i
) : Seat(position) {

    private fun nextState(applicableSeats: List<Seat>): Seat = if (
        applicableSeats.filterIsInstance<OccupiedSeat>().isEmpty()
    ) {
        OccupiedSeat(position)
    } else {
        this
    }

    override fun nextStateSimple(applicableSeats: List<Seat>): Seat = nextState(applicableSeats)
    override fun nextStateComplex(applicableSeats: List<Seat>): Seat = nextState(applicableSeats)

}
