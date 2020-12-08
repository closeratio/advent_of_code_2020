package com.closeratio.aoc2020

data class Accumulate(
    val amount: Long
): Instruction() {

    override fun execute(computer: Computer) {
        computer.accumulator += amount
        computer.programCounter++
    }

    companion object {
        fun from(line: String): Accumulate = Accumulate(
            line.split(" ").last().toLong()
        )
    }

}