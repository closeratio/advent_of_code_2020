package com.closeratio.aoc2020.instruction

import com.closeratio.aoc2020.Direction.*
import com.closeratio.aoc2020.State

data class MoveForward(val amount: Int) : Instruction() {

    override fun nextState(state: State): State = State(
        state.direction,
        when (state.direction) {
            NORTH -> state.position.up(amount)
            EAST -> state.position.right(amount)
            SOUTH -> state.position.down(amount)
            WEST -> state.position.left(amount)
        }
    )

}