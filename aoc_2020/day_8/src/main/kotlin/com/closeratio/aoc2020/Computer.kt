package com.closeratio.aoc2020

class Computer(
    val instructions: List<Instruction>,
) {

    var programCounter: Int = 0
    var accumulator: Long = 0

    fun iterate() {
        instructions[programCounter].execute(this)
    }

    fun iterateUntilInstructionRepeated() {
        val visited = hashSetOf<Int>()

        while (programCounter !in visited) {
            visited += programCounter
            iterate()
        }
    }

    companion object {

        fun from(input: String): Computer = Computer(input
            .trim()
            .split("\n")
            .map { it.trim() }
            .map {
                when (it.take(3)) {
                    "nop" -> NoOp.from(it)
                    "acc" -> Accumulate.from(it)
                    "jmp" -> Jump.from(it)
                    else -> throw IllegalArgumentException("Unknown operation: ${it.take(3)}")
                }
            }
        )

    }

}

