package com.closeratio.aoc2020

import com.closeratio.aoc2020.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class SlopeMapTest {

    private val slopeMap = SlopeMap.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {

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
        val treeCount = slopeMap.treeCount(Vec2i(3, 1))

        assertThat(treeCount, `is`(7))
    }

    @Test
    fun treeCountMultipleDirections() {
        val treeCounts = listOf(
            Vec2i(1, 1),
            Vec2i(3, 1),
            Vec2i(5, 1),
            Vec2i(7, 1),
            Vec2i(1, 2)
        )

        val product = treeCounts.map { slopeMap.treeCount(it) }.fold(1L) { acc, curr -> acc * curr }
        assertThat(product, `is`(336))
    }

}