package com.closeratio.aoc2020.instruction

import com.closeratio.aoc2020.State

abstract class Instruction {

    abstract fun nextSimpleState(state: State): State

    abstract fun nextComplexState(state: State): State

}

