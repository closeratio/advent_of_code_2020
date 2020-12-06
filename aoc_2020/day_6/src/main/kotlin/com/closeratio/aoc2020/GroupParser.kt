package com.closeratio.aoc2020

object GroupParser {

    fun parse(input: String): List<Group> = input
        .trim()
        .split("\n\n")
        .map { chunk ->
            Group(chunk
                .split("\n")
                .map { it.trim() }
                .map {
                    Person(it.toSet())
                }
            )
        }

}