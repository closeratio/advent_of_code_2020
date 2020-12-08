package com.closeratio.aoc2020

class NoOp(
    val value: Long
) : Instruction() {

    override fun execute(computer: Computer) {
        computer.programCounter++
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NoOp

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "NoOp(value=$value)"
    }

    companion object {
        fun from(line: String): NoOp = NoOp(
            line.split(" ").last().toLong()
        )
    }

}