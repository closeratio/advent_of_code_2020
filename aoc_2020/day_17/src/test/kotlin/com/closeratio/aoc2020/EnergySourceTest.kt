package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec3i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class EnergySourceTest {

    private val energySource = EnergySource.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
        assertThat(energySource.initialState, hasSize(5))
        assertThat(
            energySource.initialState, hasItems(
                Vec3i(1, 0, 0),
                Vec3i(2, 1, 0),
                Vec3i(0, 2, 0),
                Vec3i(1, 2, 0),
                Vec3i(2, 2, 0)
            )
        )
    }

    @Test
    fun iterateSingle() {
        val result = energySource.iterate(1)

        assertThat(result, `is`(11))
    }

    @Test
    fun iterateBootSequence() {
        val result = energySource.iterate(6)

        assertThat(result, `is`(112))
    }

}