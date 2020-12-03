package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class SlopeMapTest {

    @Test
    fun from() {
        val slopeMap = SlopeMap.from(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(slopeMap.height, `is`(11))
        assertThat(slopeMap.width, `is`(11))
        assertThat(slopeMap.trees, hasSize(37))

        assertThat(
            slopeMap.trees, hasItems(
                Vec2i(2, 0),
                Vec2i(10, 10),
                Vec2i(5, 9)
            )
        )
    }

    @Test
    fun treeCount() {
        val slopeMap = SlopeMap.from(javaClass.getResource("/test_input_1.txt").readText())
        val treeCount = slopeMap.treeCount(Vec2i(3, 1))

        assertThat(treeCount, `is`(7))
    }

}