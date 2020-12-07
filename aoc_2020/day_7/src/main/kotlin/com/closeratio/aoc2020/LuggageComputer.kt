package com.closeratio.aoc2020

class LuggageComputer(
    val bags: Set<Bag>
) {

    val bagTrees = bags.let {
        val childColours = bags
            .flatMap { it.contents.keys }
            .map { it.colour }
            .toSet()

        bags.filter { it.colour !in childColours }
    }

    fun calculateValidBags(colour: Colour): Int {
        return bagTrees
            .flatMap { it.getValidBags(colour) }
            .toSet()
            .size
    }

    fun calculateChildBagCount(colour: Colour): Int = bagTrees
        .asSequence()
        .mapNotNull { it.findBag(colour) }
        .first()
        .calculateChildBagCount()
        .toInt() - 1

    companion object {

        val BAG_COLOUR_REGEX = """^(.+) bags contain""".toRegex()
        val CONTENTS_REGEX = """(\d+) ([a-z ]+) bags?""".toRegex()

        fun from(data: String): LuggageComputer {
            val lines = data
                .trim()
                .split("\n")
                .map { it.trim() }

            val bagMap = lines
                .map { line ->
                    Bag(
                        Colour(BAG_COLOUR_REGEX.find(line)!!.groupValues[1]),
                        hashMapOf()
                    )
                }
                .associateBy { it.colour }

            lines.forEach { line ->
                val parentBag = bagMap.getValue(Colour(BAG_COLOUR_REGEX.find(line)!!.groupValues[1]))

                CONTENTS_REGEX.findAll(line).forEach {
                    val count = it.groupValues[1].toLong()
                    val childBag = bagMap.getValue(Colour(it.groupValues[2]))

                    parentBag.contents[childBag] = count
                }
            }

            return LuggageComputer(
                bagMap.values.toSet()
            )
        }
    }

}