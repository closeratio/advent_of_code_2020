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

    fun calculateSynchronisationTime(): Long = buses
        .drop(1)
        .fold(0L to buses.first().id) { (startTime, stepSize), (id, offset) ->
            var currTime = startTime
            while ((currTime + offset) % id != 0L) {
                currTime += stepSize
            }
            currTime to stepSize * id
        }
        .first

    companion object {

        fun from(data: String): BusSchedule = data
            .trim()
            .split("\n")
            .map { it.trim() }
            .let { (time, busesString) ->
                BusSchedule(
                    busesString
                        .split(",")
                        .mapIndexedNotNull { index, value ->
                            if (value.toLongOrNull() == null) {
                                null
                            } else {
                                Bus(value.toLong(), index.toLong())
                            }
                        }
                        .toSet(),
                    time.toLong()
                )
            }

    }

}