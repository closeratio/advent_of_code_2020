package com.closeratio.aoc2020

abstract class Instruction() {

    abstract fun executeV1(
        currentMask: String,
        updateMemory: (Long, Long) -> Unit,
        updateMask: (String) -> Unit
    )

    abstract fun executeV2(
        currentMask: String,
        updateMemory: (Long, Long) -> Unit,
        updateMask: (String) -> Unit
    )

}