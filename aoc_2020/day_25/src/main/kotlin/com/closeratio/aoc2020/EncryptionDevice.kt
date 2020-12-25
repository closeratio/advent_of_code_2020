package com.closeratio.aoc2020

abstract class EncryptionDevice(
    private val subjectNumber: Long,
    private val publicKey: Long
) {

    fun calculateEncryptionKey(encryptionDevice: EncryptionDevice): Long {
        val loopSize = calculateLoopSize()

        var currValue = 1L
        repeat(loopSize.toInt()) {
            currValue = (currValue * encryptionDevice.publicKey) % 20201227L
        }

        return currValue
    }

    private fun calculateLoopSize(): Long {
        var currValue = 1L
        var loopNumber = 1L

        while (true) {
            currValue = (currValue * subjectNumber) % 20201227L

            if (currValue == publicKey) {
                return loopNumber
            }

            loopNumber++
        }
    }

}