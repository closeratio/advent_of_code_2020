package com.closeratio.aoc2020

import com.closeratio.aoc2020.InitialisationProgram.Updater

data class UpdateMemory(
    val address: Long,
    val value: Long
) : Instruction() {

    override fun executeV1(
        updater: Updater
    ) {
        val valueString = value.toString(2).padStart(36, '0')
        val newValue = updater
            .mask
            .zip(valueString)
            .map { (mask, char) ->
                when (mask) {
                    '0', '1' -> mask
                    else -> char
                }
            }
            .joinToString("")
            .toLong(2)

        updater.updateMemory(address, newValue)
    }

    override fun executeV2(
        updater: Updater
    ) {
        val addresses = getAddresses(
            updater.mask,
            address.toString(2).padStart(36, '0')
        )

        addresses.forEach {
            updater.updateMemory(it.toLong(2), value)
        }
    }

    private fun getAddresses(
        mask: String,
        addressPadded: String
    ): List<String> = if (!mask.contains("X")) {
        mask.zip(addressPadded)
            .map { (mask, addr) ->
                when (mask) {
                    '1' -> mask
                    '2' -> '0'
                    '3' -> '1'
                    else -> addr
                }
            }
            .joinToString("")
            .let {
                listOf(it)
            }
    } else {
        // A bit hacky, but we use '2' and '3' to represent the 2 floating value states, and these are then mapped to
        // their corresponding binary values in the the recursive call
        listOf(2, 3)
            .flatMap {
                getAddresses(
                    mask.replaceFirst('X', it.toString()[0]),
                    addressPadded
                )
            }
    }
}