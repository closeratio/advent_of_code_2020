package com.closeratio.aoc2020

data class Jump(
    val amount: Long
): Instruction() {

    override fun execute(computer: Computer) {
        computer.programCounter += amount.toInt()
    }

    companion object {
        fun from(line: String): Jump = Jump(
            line.split(" ").last().toLong()
        )
    }

}