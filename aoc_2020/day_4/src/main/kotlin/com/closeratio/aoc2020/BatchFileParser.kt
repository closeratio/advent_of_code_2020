package com.closeratio.aoc2020

object BatchFileParser {

    fun parse(data: String): List<PassportData> = data
        .split("\n\n")
        .map {
            PassportData.parse(it)
        }

}
