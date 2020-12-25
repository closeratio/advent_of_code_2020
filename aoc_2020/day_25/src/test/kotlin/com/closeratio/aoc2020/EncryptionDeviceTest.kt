package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class EncryptionDeviceTest {

    private val card = Card(7, 5764801L)
    private val door = Door(7, 17807724)

    @Test
    fun calculateEncryptionKey() {
        assertThat(card.calculateEncryptionKey(door), `is`(14897079L))
        assertThat(door.calculateEncryptionKey(card), `is`(14897079L))
    }
}