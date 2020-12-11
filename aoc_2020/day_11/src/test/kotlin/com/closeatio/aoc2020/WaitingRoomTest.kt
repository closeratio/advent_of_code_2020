package com.closeatio.aoc2020

import com.closeratio.aoc2020.EmptySeat
import com.closeratio.aoc2020.OccupiedSeat
import com.closeratio.aoc2020.WaitingRoom
import com.closeratio.aoc2020.math.Vec2i
import com.closeratio.aoc2020.math.Vec2i.Companion.ZERO
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WaitingRoomTest {

    private val room = WaitingRoom.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from_emptyRoom() {
        assertThat(room.seats, aMapWithSize(71))

        assertThat(room.seats.values.filterIsInstance<EmptySeat>(), hasSize(71))
        assertThat(room.seats.values.filterIsInstance<OccupiedSeat>(), empty())

        assertThat(room.seats, hasEntry(ZERO, EmptySeat(ZERO)))
        assertThat(room.seats, hasEntry(Vec2i(2, 0), EmptySeat(Vec2i(2, 0))))
    }

    @Test
    fun from_occupiedRoom() {
        val occupiedRoom = WaitingRoom.from(javaClass.getResource("/test_input_1_5_iterations_simple.txt").readText())

        assertThat(occupiedRoom.seats, aMapWithSize(71))

        assertThat(occupiedRoom.seats.values.filterIsInstance<EmptySeat>(), hasSize(34))
        assertThat(occupiedRoom.seats.values.filterIsInstance<OccupiedSeat>(), hasSize(37))

        assertThat(occupiedRoom.seats, hasEntry(ZERO, OccupiedSeat(ZERO)))
        assertThat(occupiedRoom.seats, hasEntry(Vec2i(2, 0), OccupiedSeat(Vec2i(2, 0))))
        assertThat(occupiedRoom.seats, hasEntry(Vec2i(3, 0), EmptySeat(Vec2i(3, 0))))
    }

    @ParameterizedTest(name = "{0} iterations")
    @MethodSource("getIterationData")
    fun iterateSimple(
        iterationCount: Int,
        expected: WaitingRoom
    ) {
        val states = arrayListOf(room)
        repeat(iterationCount) {
            states += states.last().iterateSimple()
        }

        assertThat(states.last(), `is`(expected))
    }

    @Test
    fun iterateUntilStableSimple() {
        val result = room.iterateUntilStableSimple()

        assertThat(result, `is`(37))
    }

    @Test
    fun iterateUntilStableComplex() {
        val result = room.iterateUntilStableComplex()

        assertThat(result, `is`(26))
    }

    companion object {

        @JvmStatic
        fun getIterationData(): List<Arguments> = listOf(
            "/test_input_1_1_iteration_simple.txt",
            "/test_input_1_2_iterations_simple.txt",
            "/test_input_1_3_iterations_simple.txt",
            "/test_input_1_4_iterations_simple.txt",
            "/test_input_1_5_iterations_simple.txt"
        ).mapIndexed { index, resourcePath ->
            Arguments.of(
                index + 1, WaitingRoom.from(WaitingRoomTest::class.java.getResource(resourcePath).readText())
            )
        }

    }

}