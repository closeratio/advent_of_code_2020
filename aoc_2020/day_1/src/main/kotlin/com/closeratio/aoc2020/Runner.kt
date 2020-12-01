package com.closeratio.aoc2020

object Runner {

    fun part1() {
        val report = ExpenseReport.from(javaClass.getResource("/input.txt").readText())
        val product = report.calculatePairProduct(2020)
        println(product)
    }

}

fun main() {
    Runner.part1()
}