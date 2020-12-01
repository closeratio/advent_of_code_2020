package com.closeratio.aoc2020

object Runner: AbstractRunner() {

    override fun part1() {
        val report = ExpenseReport.from(javaClass.getResource("/input.txt").readText())
        val product = report.calculatePairProduct(2020)
        println(product)
    }

    override fun part2() {
        val report = ExpenseReport.from(javaClass.getResource("/input.txt").readText())
        val product = report.calculateTripleProduct(2020)
        println(product)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Runner.runBothParts()
    }
}