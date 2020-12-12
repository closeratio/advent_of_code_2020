package com.closeratio.aoc2020.instruction

import com.closeratio.aoc2020.State
import com.closeratio.aoc2020.math.toRadians

data class TurnRight(val amount: Int) : Instruction() {

    override fun nextSimpleState(state: State): State = State(
        (1..(amount / 90)).fold(state.direction) { acc, _ -> acc.right() },
        state.shipPosition,
        state.waypointPosition
    )

    override fun nextComplexState(state: State): State {
        val delta = (state.waypointPosition - state.shipPosition).rotate(amount.toRadians())

        return State(
            state.direction,
            state.shipPosition,
            state.shipPosition + delta
        )
    }

}
