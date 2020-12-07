package com.closeratio.aoc2020

class Bag(
    val colour: Colour,
    val contents: HashMap<Bag, Long>
) {

    fun getValidBags(colour: Colour): Set<Bag> {
        val childBags = contents
            .keys
            .flatMap { it.getValidBags(colour) }
            .toSet()

        val thisBag = if (childBags.isNotEmpty() || contents.any { it.key.colour == colour }) {
            this
        } else {
            null
        }

        return (childBags + thisBag).filterNotNull().toSet()
    }

    fun findBag(colour: Colour): Bag? {
        if (this.colour == colour) {
            return this
        }

        return contents
            .keys
            .map { it.findBag(colour) }
            .firstOrNull()
    }

    fun calculateChildBagCount(): Long {
        return 1 + contents
            .map { (bag, count) ->
                bag.calculateChildBagCount() * count
            }
            .sum()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bag

        if (colour != other.colour) return false

        return true
    }

    override fun hashCode(): Int {
        return colour.hashCode()
    }

    override fun toString(): String {
        return "Bag(colour=$colour)"
    }

}

