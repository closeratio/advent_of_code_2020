package com.closeratio.aoc2020

data class BagNode(
    val bag: Bag,
    val children: List<BagNode>
) {

    fun getValidBags(colour: Colour): List<Bag> {
        val childBags = children.flatMap { it.getValidBags(colour) }

        val thisBag = if (children.any { it.bag.colour == colour } || childBags.isNotEmpty()) {
            bag
        } else {
            null
        }

        return (childBags + thisBag).filterNotNull()

    }

}