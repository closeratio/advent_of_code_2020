package com.closeratio.aoc2020

abstract class Instruction() {

    abstract fun execute(
        currentMask: String,
        updateMemory: (Long, Long) -> Unit,
        updateMask: (String) -> Unit
    )

}