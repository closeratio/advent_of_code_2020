package com.closeratio.aoc2020

import com.closeratio.aoc2020.Direction.EAST
import com.closeratio.aoc2020.instruction.*
import com.closeratio.aoc2020.math.Vec2i
import com.closeratio.aoc2020.math.Vec2i.Companion.ZERO

class Ship(
    val instructions: List<Instruction>,
    val initialState: State
) {

    private fun moveObject(
        stateIterator: Instruction.(State) -> State
    ): Int {
        val states = arrayListOf(initialState)

        instructions.forEach {
            states.add(
                it.stateIterator(states.last())
            )
        }

        return states.last().shipPosition.manhattan()
    }

    fun moveSimple(): Int = moveObject { nextSimpleState(it) }

    fun moveComplex(): Int = moveObject { nextComplexState(it) }

    companion object {

        fun from(input: String): Ship = Ship(
            input.trim()
                .split("\n")
                .map { it.trim() }
                .map {
                    val action = it[0]
                    val amount = it.drop(1).toInt()
                    when (action) {
                        'N' -> MoveNorth(amount)
                        'E' -> MoveEast(amount)
                        'S' -> MoveSouth(amount)
                        'W' -> MoveWest(amount)
                        'L' -> TurnLeft(amount)
                        'R' -> TurnRight(amount)
                        'F' -> MoveForward(amount)
                        else -> throw IllegalArgumentException("Unknown instruction: $it")
                    }
                },
            State(EAST, ZERO, Vec2i(10, -1))
        )

    }

}

