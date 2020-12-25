package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val card = Card(7L, 14205034L)
    private val door = Door(7L, 18047856L)

    override fun part1(): Long = card.calculateEncryptionKey(door)

    override fun part2(): Long = 0L

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}