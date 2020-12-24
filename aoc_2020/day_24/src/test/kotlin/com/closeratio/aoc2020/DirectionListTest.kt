package com.closeratio.aoc2020

import com.closeratio.aoc2020.Direction.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInRelativeOrder.containsInRelativeOrder
import org.junit.jupiter.api.Test

class DirectionListTest {

    private val directionLists = DirectionList.parse(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun parse() {
        assertThat(directionLists, hasSize(20))

        assertThat(
            directionLists.first().directions,
            containsInRelativeOrder(
                SOUTHEAST,
                SOUTHEAST,
                NORTHWEST,
                NORTHEAST,
                NORTHEAST,
                NORTHEAST,
                WEST,
                SOUTHEAST,
                EAST,
                WEST,
                SOUTHWEST
            )
        )
    }

}