package com.closeratio.aoc2020

class Cup(
    val value: Long
) {

    lateinit var next: Cup

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cup) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "Cup(value=$value)"
    }

}