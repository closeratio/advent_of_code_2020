package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder
import org.junit.jupiter.api.Test

class XmasListTest {

    val xmasList = XmasList.from(javaClass.getResource("/test_input_1.txt").readText(), 5)

    @Test
    fun from() {
        assertThat(xmasList.windowSize, `is`(5))
        assertThat(xmasList.list, hasSize(20))
        assertThat(
            xmasList.list, IsIterableContainingInOrder.contains(
                35L, 20L, 15L, 25L, 47L, 40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 127L, 219L, 299L, 277L, 309L,
                576L
            )
        )
    }

    @Test
    fun findFirstInvalidNumber() {
        val result: Long = xmasList.findFirstInvalidNumber()

        assertThat(result, `is`(127L))
    }

    @Test
    fun findEncryptionWeakness() {
        val result: Long = xmasList.findEncryptionWeakness(127L)

        assertThat(result, `is`(62L))
    }

}