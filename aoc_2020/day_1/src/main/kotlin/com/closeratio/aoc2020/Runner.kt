package com.closeratio.aoc2020

object Runner: AbstractRunner<Long>() {

    override fun part1(): Long {
        val report = ExpenseReport.from(javaClass.getResource("/input.txt").readText())
        return report.calculatePairProduct(2020)
    }

    override fun part2(): Long {
        val report = ExpenseReport.from(javaClass.getResource("/input.txt").readText())
        return report.calculateTripleProduct(2020)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Runner.runBothParts()
    }
}