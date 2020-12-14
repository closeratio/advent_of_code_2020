package com.closeratio.aoc2020

import com.closeratio.aoc2020.InitialisationProgram.Updater

abstract class Instruction {

    abstract fun executeV1(
        updater: Updater
    )

    abstract fun executeV2(
        updater: Updater
    )

}