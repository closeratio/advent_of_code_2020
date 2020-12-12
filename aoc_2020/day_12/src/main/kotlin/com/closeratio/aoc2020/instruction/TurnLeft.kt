package com.closeratio.aoc2020.instruction

import com.closeratio.aoc2020.State

data class TurnLeft(val amount: Int) : Instruction() {

    override fun nextState(state: State): State = State(
        (1..(amount / 90)).fold(state.direction) { acc, _ -> acc.left() },
        state.position
    )

}