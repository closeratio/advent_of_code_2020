package com.closeratio.aoc2020

data class BusSchedule(
    val buses: Set<Bus>,
    val startTime: Long
) {

    fun calculateBusAndWaitTime(): Pair<Bus, Long> = buses
        .map { bus ->
            bus to (startTime % bus.id).let { remainder ->
                if (remainder == 0L) {
                    0L
                } else {
                    bus.id - remainder
                }
            }
        }
        .minByOrNull { it.second }!!

    companion object {

        fun from(data: String): BusSchedule = data
            .trim()
            .split("\n")
            .map { it.trim() }
            .let { (time, busesString) ->
                BusSchedule(
                    busesString
                        .split(",")
                        .mapNotNull { it.toLongOrNull() }
                        .map { Bus(it) }
                        .toSet(),
                    time.toLong()
                )
            }

    }

}