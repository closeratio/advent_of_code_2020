package com.closeratio.aoc2020

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TicketProcessorTest {

    private val processor = TicketProcessor.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
        assertThat(
            processor, `is`(
                TicketProcessor(
                    listOf(
                        FieldRule("class", listOf(1L..3L, 5L..7L)),
                        FieldRule("row", listOf(6L..11L, 33L..44L)),
                        FieldRule("seat", listOf(13L..40L, 45L..50L))
                    ),
                    Ticket(
                        listOf(7, 1, 14)
                    ),
                    listOf(
                        Ticket(listOf(7, 3, 47)),
                        Ticket(listOf(40, 4, 50)),
                        Ticket(listOf(55, 2, 20)),
                        Ticket(listOf(38, 6, 12))
                    )
                )
            )
        )
    }

    @Test
    fun getInvalidValueSum() {
        val result = processor.getInvalidValueSum()

        assertThat(result, `is`(71L))
    }

}