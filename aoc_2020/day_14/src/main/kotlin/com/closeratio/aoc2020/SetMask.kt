package com.closeratio.aoc2020

import com.closeratio.aoc2020.InitialisationProgram.Updater

data class SetMask(
    val newMask: String
) : Instruction() {

    override fun executeV1(
        updater: Updater
    ) {
        updater.updateMask(newMask)
    }

    override fun executeV2(
        updater: Updater
    ) = executeV1(updater)
}