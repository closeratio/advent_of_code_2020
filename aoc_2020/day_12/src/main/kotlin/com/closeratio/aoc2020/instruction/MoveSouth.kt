package com.closeratio.aoc2020.instruction

import com.closeratio.aoc2020.State

data class MoveSouth(val amount: Int) : Instruction() {

    override fun nextSimpleState(state: State): State = State(
        state.direction,
        state.shipPosition.down(amount),
        state.waypointPosition
    )

    override fun nextComplexState(state: State): State = State(
        state.direction,
        state.shipPosition,
        state.waypointPosition.down(amount)
    )

}