package com.closeratio.aoc2020

data class InitialisationProgram(
    val instructions: List<Instruction>
) {

    private fun executeAndSum(
        execute: Instruction.(
            updater: Updater
        ) -> Unit
    ): Long {
        var mask = ""
        val memoryMap = hashMapOf<Long, Long>()

        instructions.forEach { instruction ->
            instruction.execute(
                Updater(
                    mask,
                    { index, value -> memoryMap[index] = value },
                    { newMask -> mask = newMask }
                )
            )
        }

        return memoryMap.values.sum()
    }

    fun executeAndSumV1(): Long = executeAndSum(Instruction::executeV1)

    fun executeAndSumV2(): Long = executeAndSum(Instruction::executeV2)

    companion object {

        fun from(data: String): InitialisationProgram = data
            .trim()
            .split("\n")
            .map { it.trim() }
            .map { line ->
                val (operation, value) = line.split("=").map { it.trim() }

                when {
                    operation == "mask" -> SetMask(value)
                    operation.startsWith("mem") -> UpdateMemory(
                        """\[(\d+)\]""".toRegex().find(operation)!!.groupValues[1].toLong(),
                        value.toLong()
                    )
                    else -> throw IllegalArgumentException("Unrecognised operation: $operation")
                }
            }
            .let {
                InitialisationProgram(it)
            }

    }

    class Updater(
        val mask: String,
        val updateMemory: (Long, Long) -> Unit,
        val updateMask: (String) -> Unit
    )

}

