package com.closeratio.aoc2020

data class SetMask(
    val newMask: String
) : Instruction() {

    override fun execute(
        currentMask: String,
        updateMemory: (Long, Long) -> Unit,
        updateMask: (String) -> Unit
    ) {
        updateMask(newMask)
    }

}