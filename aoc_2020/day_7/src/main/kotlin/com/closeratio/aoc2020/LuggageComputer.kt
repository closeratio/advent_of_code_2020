package com.closeratio.aoc2020

class LuggageComputer(
    val bags: Set<Bag>
) {

    val bagTrees = bags.let {
        val childColours = bags.flatMap { it.contents.keys }.toSet()
        val colourMap = bags.associateBy { it.colour }

        bags
            .filter { it.colour !in childColours }
            .map {
                getBagNode(
                    colourMap,
                    it.colour
                )
            }
    }

    private fun getBagNode(
        bagMap: Map<Colour, Bag>,
        colour: Colour
    ): BagNode = BagNode(
        bagMap.getValue(colour),
        bagMap.getValue(colour).contents.keys.map {
            getBagNode(bagMap, it)
        }
    )

    fun calculateBagCount(colour: Colour): Int {
        return bagTrees
            .flatMap { it.getValidBags(colour) }
            .toSet()
            .size
    }

    companion object {
        fun from(data: String): LuggageComputer = data
            .trim()
            .split("\n")
            .map { Bag.parse(it.trim()) }
            .toSet()
            .let {
                LuggageComputer(it)
            }
    }

}