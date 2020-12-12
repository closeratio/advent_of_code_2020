package com.closeratio.aoc2020.instruction

import com.closeratio.aoc2020.Direction.*
import com.closeratio.aoc2020.State

data class MoveForward(val amount: Int) : Instruction() {

    override fun nextSimpleState(state: State): State = State(
        state.direction,
        when (state.direction) {
            NORTH -> state.shipPosition.up(amount)
            EAST -> state.shipPosition.right(amount)
            SOUTH -> state.shipPosition.down(amount)
            WEST -> state.shipPosition.left(amount)
        },
        state.waypointPosition
    )

    override fun nextComplexState(state: State): State {
        val delta = state.waypointPosition - state.shipPosition
        val newShipPost = (1..amount).fold(state.shipPosition) { acc, _ -> acc + delta }

        return State(
            state.direction,
            newShipPost,
            newShipPost + delta
        )
    }

}