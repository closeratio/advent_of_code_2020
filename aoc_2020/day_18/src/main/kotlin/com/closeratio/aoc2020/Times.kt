package com.closeratio.aoc2020

class Times: Element() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Times) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Times()"
    }

}