package com.closeratio.aoc2020

data class SetMask(
    val newMask: String
) : Instruction() {

    override fun executeV1(
        currentMask: String,
        updateMemory: (Long, Long) -> Unit,
        updateMask: (String) -> Unit
    ) {
        updateMask(newMask)
    }

    override fun executeV2(
        currentMask: String,
        updateMemory: (Long, Long) -> Unit,
        updateMask: (String) -> Unit
    ) = executeV1(currentMask, updateMemory, updateMask)
}