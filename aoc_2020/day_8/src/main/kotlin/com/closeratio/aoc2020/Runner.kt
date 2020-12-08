package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    override fun part1(): Long = Computer
        .from(javaClass.getResource("/input.txt").readText())
        .let {
            it.iterateUntilLoopingOrFinished()
            it.accumulator
        }

    override fun part2(): Long = Computer
        .from(javaClass.getResource("/input.txt").readText())
        .let { computer ->
            // Generate replacement instructions
            computer.instructions
                .mapIndexed { index, instruction -> index to instruction }
                .filter { (_, instruction) -> instruction is NoOp || instruction is Jump }
                .map { (replacementIndex, instruction) ->
                    Triple(
                        computer,
                        replacementIndex,
                        when (instruction) {
                            is NoOp -> Jump(instruction.value)
                            is Jump -> NoOp(instruction.amount)
                            else -> throw IllegalStateException("Unexpected instruction type: $instruction")
                        }
                    )
                }
        }
        .asSequence()
        .map { (computer, replacementIndex, replacementInstruction) ->
            // Create another computer with the modified instruction
            val modifiedComputer = Computer(
                ArrayList(computer.instructions).apply {
                    set(replacementIndex, replacementInstruction)
                }
            )

            val result = modifiedComputer.iterateUntilLoopingOrFinished()
            result to modifiedComputer.accumulator
        }
        .first { it.first }
        .second

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}