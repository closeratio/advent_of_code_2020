package com.closeratio.aoc2020

data class TicketProcessor(
    val fieldRules: List<FieldRule>,
    val myTicket: Ticket,
    val nearbyTickets: List<Ticket>
) {

    fun getInvalidValueSum(): Long = nearbyTickets
        .flatMap { it.getInvalidValues(fieldRules) }
        .sum()

    companion object {

        @JvmStatic
        fun from(input: String): TicketProcessor = input
            .trim()
            .split("\n\n")
            .let { (fieldRulesString, myTicketString, nearbyTicketsString) ->
                TicketProcessor(
                    fieldRulesString.split("\n").map { parseFieldRule(it) },
                    parseTicket(myTicketString.split("\n").last()),
                    nearbyTicketsString.split("\n").drop(1).map { parseTicket(it) }
                )
            }

        private fun parseFieldRule(input: String): FieldRule {
            val (name, ranges) = input.split(":").map { it.trim() }

            return FieldRule(
                name,
                ranges.split(" or ")
                    .map {
                        it.split("-")
                            .map { it.toLong() }
                            .let { (lower, upper) -> lower..upper }
                    }
            )
        }

        private fun parseTicket(input: String): Ticket = Ticket(
            input.split(",").map { it.toLong() }
        )

    }

}
