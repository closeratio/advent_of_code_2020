package com.closeratio.aoc2020

class NoOp(
    val value: Long
) : Instruction() {

    override fun execute(computer: Computer) {
        computer.programCounter++
    }

    companion object {
        fun from(line: String): NoOp = NoOp(
            line.split(" ").last().toLong()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "NoOp()"
    }

}