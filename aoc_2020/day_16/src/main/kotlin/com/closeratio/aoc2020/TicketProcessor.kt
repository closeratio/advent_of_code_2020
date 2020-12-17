package com.closeratio.aoc2020

data class TicketProcessor(
    val fieldRules: List<FieldRule>,
    val myTicket: Ticket,
    val nearbyTickets: List<Ticket>
) {

    fun getInvalidValueSum(): Long = nearbyTickets
        .flatMap { it.getInvalidValues(fieldRules) }
        .sum()

    private fun findInvalidTickets(): List<Ticket> = nearbyTickets
        .filter { it.isInvalid(fieldRules) }

    fun calculateDepartureValue(
        requiredFields: Set<String>
    ): Long {
        val validTickets = (nearbyTickets + myTicket) - findInvalidTickets().toSet()

        val foundFields = hashMapOf<String, Int>()
        val eliminatedIndices = hashSetOf<Int>()
        val remainingFields = fieldRules.toMutableSet()

        while (remainingFields.isNotEmpty()) {
            remainingFields.firstOrNull { fieldRule ->
                val validIndices = validTickets
                    .map { ticket -> ticket.getValidIndices(fieldRule) }
                    .reduce { acc, curr -> acc.intersect(curr) }
                    .filter { it !in eliminatedIndices }

                if (validIndices.size == 1) {
                    foundFields[fieldRule.name] = validIndices.first()
                    eliminatedIndices.add(validIndices.first())
                    remainingFields.remove(fieldRule)
                    true
                } else {
                    false
                }
            } ?: throw IllegalStateException("Unable to eliminate any more fields")
        }

        return requiredFields
            .map { foundFields.getValue(it) }
            .map { myTicket.values[it] }
            .reduce { acc, curr -> acc * curr }
    }

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
