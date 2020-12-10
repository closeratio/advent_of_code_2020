package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test

class BagTest {

    @Test
    fun from() {
        val bag = Bag.from(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(bag.adapters, hasSize(11))
        assertThat(
            bag.adapters, contains(
                Adapter(16),
                Adapter(10),
                Adapter(15),
                Adapter(5),
                Adapter(1),
                Adapter(11),
                Adapter(7),
                Adapter(19),
                Adapter(6),
                Adapter(12),
                Adapter(4)
            )
        )
    }

    @Test
    fun joltageDifferences_testInput1() {
        val bag = Bag.from(javaClass.getResource("/test_input_1.txt").readText())
        val differences: Map<Int, Int> = bag.joltageDifferences()

        assertThat(differences, aMapWithSize(2))

        assertThat(differences, hasEntry(1, 7))
        assertThat(differences, hasEntry(3, 5))
    }

    @Test
    fun joltageDifferences_testInput2() {
        val bag = Bag.from(javaClass.getResource("/test_input_2.txt").readText())
        val differences: Map<Int, Int> = bag.joltageDifferences()

        assertThat(differences, aMapWithSize(2))

        assertThat(differences, hasEntry(1, 22))
        assertThat(differences, hasEntry(3, 10))
    }

}