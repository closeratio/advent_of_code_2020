package com.closeratio.aoc2020

class Computer(
    val instructions: MutableList<Instruction>,
) {

    var programCounter: Int = 0
    var accumulator: Long = 0

    fun iterateUntilLoopingOrFinished(): Boolean {
        val visited = linkedSetOf<Int>()
        val validPcRange = 0 until instructions.size

        while (programCounter !in visited && programCounter in validPcRange) {
            visited += programCounter
            instructions[programCounter].execute(this)
        }

        return programCounter == instructions.size
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
            .toMutableList()
        )

    }

}

