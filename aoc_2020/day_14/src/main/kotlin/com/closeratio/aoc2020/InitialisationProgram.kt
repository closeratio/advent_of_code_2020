package com.closeratio.aoc2020

data class InitialisationProgram(
    val instructions: List<Instruction>
) {

    fun executeAndSum(): Long {
        var mask = ""
        val memoryMap = hashMapOf<Long, Long>()

        instructions.forEach { instruction ->
            instruction.execute(
                mask,
                { index, value -> memoryMap[index] = value }
            ) { newMask ->
                mask = newMask
            }
        }

        return memoryMap.values.sum()
    }

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

}

