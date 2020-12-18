package com.closeratio.aoc2020

class Plus: Element() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Plus) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Plus()"
    }

}