package com.closeratio.aoc2020

data class Group(
    val people: List<Person>
) {

    fun allAnswerCount(): Int = people
        .fold(setOf<Char>()) { acc, curr -> acc + curr.answers }
        .size

    fun commonAnswerCount(): Int = people
        .map { it.answers }
        .reduce { acc, curr -> acc.intersect(curr) }
        .size

}