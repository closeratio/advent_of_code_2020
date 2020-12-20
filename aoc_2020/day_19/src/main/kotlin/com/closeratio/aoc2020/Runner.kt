package com.closeratio.aoc2020

object Runner : AbstractRunner<Long>() {

    private val processor = MessageProcessor.from(javaClass.getResource("/input.txt").readText())

    override fun part1(): Long = processor.getMatchingMessages(RuleId(0)).toLong()

    override fun part2(): Long = MessageProcessor
        .from(
            javaClass.getResource("/input.txt")
                .readText()
                .replace("8: 42", "8: 42 | 42 8")
                .replace("11: 42 31", "11: 42 31 | 42 11 31")
        )
        .getMatchingMessages(RuleId(0))
        .toLong()

    @JvmStatic
    fun main(args: Array<String>) {
        runBothParts()
    }

}