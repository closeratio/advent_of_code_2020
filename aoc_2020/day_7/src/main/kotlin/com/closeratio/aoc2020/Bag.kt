package com.closeratio.aoc2020

data class Bag(
    val colour: Colour,
    val contents: Map<Colour, Long>
) {

    companion object {

        val BAG_COLOUR_REGEX = """^(.+) bags contain""".toRegex()
        val CONTENTS_REGEX = """(\d+) ([a-z ]+) bags?""".toRegex()

        fun parse(line: String): Bag {
            val bagColour = (BAG_COLOUR_REGEX.find(line) ?: throw IllegalArgumentException("Bad line: $line"))
                .groupValues[1]
                .let { Colour(it) }

            val contents: Map<Colour, Long> = CONTENTS_REGEX
                .findAll(line)
                .map {
                    Colour(it.groupValues[2]) to it.groupValues[1].toLong()
                }
                .toMap()

            return Bag(
                bagColour,
                contents
            )
        }

    }

}

