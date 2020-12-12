package com.closeratio.aoc2020.instruction

import com.closeratio.aoc2020.State

data class MoveNorth(val amount: Int) : Instruction() {

    override fun nextState(state: State): State = State(
        state.direction,
        state.position.up(amount)
    )

}