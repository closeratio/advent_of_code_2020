package com.closeratio.aoc2020

import com.closeratio.aoc2020.Direction.EAST
import com.closeratio.aoc2020.instruction.MoveForward
import com.closeratio.aoc2020.instruction.MoveNorth
import com.closeratio.aoc2020.instruction.TurnRight
import com.closeratio.aoc2020.math.Vec2i
import com.closeratio.aoc2020.math.Vec2i.Companion.ZERO
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class ShipTest {

    private val ship = Ship.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
        assertThat(ship.instructions, hasSize(5))
        assertThat(
            ship.instructions, contains(
                MoveForward(10),
                MoveNorth(3),
                MoveForward(7),
                TurnRight(90),
                MoveForward(11)
            )
        )

        assertThat(
            ship.initialState, `is`(
                State(
                    EAST,
                    ZERO,
                    Vec2i(10, -1)
                )
            )
        )
    }

    @Test
    fun moveSimple() {
        val result = ship.moveSimple()

        assertThat(result, `is`(25))
    }

    @Test
    fun moveComplex() {
        val result = ship.moveComplex()

        assertThat(result, `is`(286))
    }

}