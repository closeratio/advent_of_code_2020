package com.closeratio.aoc2020

data class UpdateMemory(
    val address: Long,
    val value: Long
) : Instruction() {

    override fun executeV1(
        currentMask: String,
        updateMemory: (Long, Long) -> Unit,
        updateMask: (String) -> Unit
    ) {
        val valueString = value.toString(2)
        val valueStringPadded = (1..(36 - valueString.length)).joinToString("") { "0" } + valueString
        val newValue = currentMask
            .zip(valueStringPadded)
            .map { (mask, char) ->
                when (mask) {
                    '0', '1' -> mask
                    else -> char
                }
            }
            .joinToString("")
            .toLong(2)

        updateMemory(address, newValue)
    }

    override fun executeV2(currentMask: String, updateMemory: (Long, Long) -> Unit, updateMask: (String) -> Unit) {
        TODO("Not yet implemented")
    }
}