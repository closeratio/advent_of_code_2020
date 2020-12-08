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
            computer.instructions
                .mapIndexed { index, instruction -> index to instruction }
                .filter { (_, instruction) -> instruction is NoOp || instruction is Jump }
                .map { (replacementIndex, _) ->
                    val modifiedComputer = Computer(
                        computer.instructions
                            .mapIndexed { index, instruction ->
                                if (index != replacementIndex) {
                                    instruction
                                } else {
                                    when (instruction) {
                                        is NoOp -> Jump(instruction.value)
                                        is Jump -> NoOp(instruction.amount)
                                        else -> throw IllegalStateException("Unexpected instruction type: $instruction")
                                    }
                                }
                            }
                            .toMutableList()
                    )

                    val result = modifiedComputer.iterateUntilLoopingOrFinished()
                    result to modifiedComputer.accumulator
                }
                .first { (result, _) -> result }
                .second
        }

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}